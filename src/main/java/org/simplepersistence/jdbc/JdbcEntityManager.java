package org.simplepersistence.jdbc;

import org.simplepersistence.EntityManager;
import org.simplepersistence.naming.EntityNamingRule;
import org.simplepersistence.naming.PropertyNamingRule;
import com.google.common.base.Joiner;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.util.Map;

import static org.simplepersistence.TemporaryDatabaseStuff.executeAtDB;
import static com.google.common.base.Throwables.propagate;
import static com.google.common.collect.Maps.newHashMap;
import static com.google.common.collect.Maps.newLinkedHashMap;
import static java.text.MessageFormat.format;
import static java.util.Collections.nCopies;

public class JdbcEntityManager implements EntityManager {
    private final Connection connection;
    private final PropertyNamingRule propertyNamingRule;
    private final EntityNamingRule entityNamingRule;
    private static final String INSERT_STATEMENT = "insert into {0} ({1}) values ({2})";

    public JdbcEntityManager(final Connection connection,
                         final EntityNamingRule entityNamingRule,
                         final PropertyNamingRule propertyNamingRule) {
        this.connection = connection;
        this.propertyNamingRule = propertyNamingRule;
        this.entityNamingRule = entityNamingRule;
    }

    public void insert(Object object) {
        String entityName = entityNamingRule.apply(object.getClass());
        Map<String, Object> properties = properties(object);
        insert(entityName, properties);
    }

    private Map<String, Object> properties(final Object object) {
        Map<String, Object> properties = newLinkedHashMap();
        for (Field field : object.getClass().getDeclaredFields()) {
            String propertyName = propertyNamingRule.apply(field);
            properties.put(propertyName, getValue(object, field));
        }
        return properties;
    }

    private void insert(final String tableName, final Map<String, Object> columns) {
        String formattedInsert = insertStatement(tableName, columns);
        executeAtDB(connection, formattedInsert, columns.values().toArray());
    }

    private String insertStatement(final String tableName, final Map<String, Object> columns) {
        final Joiner commaJoiner = Joiner.on(", ");
        return format(INSERT_STATEMENT,
                tableName,
                commaJoiner.join(columns.keySet()),
                commaJoiner.join(nCopies(columns.size(), "?"))
        );
    }

    private Object getValue(final Object object, final Field field) {
        try {
            field.setAccessible(true);
            return field.get(object);
        } catch (IllegalAccessException e) {
            throw propagate(e);
        }
    }

    public void update(Object object) {

    }

    public void delete(Object object) {

    }

    public void findAll(Class clazz) {

    }
}
