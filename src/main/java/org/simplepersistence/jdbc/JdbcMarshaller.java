package org.simplepersistence.jdbc;

import com.google.common.base.Joiner;
import org.simplepersistence.PhysicalMarshaller;
import org.simplepersistence.jdbc.rule.EntityNamingRule;
import org.simplepersistence.jdbc.rule.PropertyNamingRule;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.util.Map;

import static com.google.common.base.Throwables.propagate;
import static com.google.common.collect.Maps.newLinkedHashMap;
import static java.text.MessageFormat.format;
import static java.util.Collections.nCopies;
import static org.simplepersistence.jdbc.JdbcUtils.executeAtDB;

public class JdbcMarshaller implements PhysicalMarshaller {

    private final Connection connection;
    private final PropertyNamingRule propertyNamingRule;
    private final EntityNamingRule entityNamingRule;

    public JdbcMarshaller(final Connection connection,
                          final EntityNamingRule entityNamingRule,
                          final PropertyNamingRule propertyNamingRule) {
        this.connection = connection;
        this.propertyNamingRule = propertyNamingRule;
        this.entityNamingRule = entityNamingRule;
    }

    public void save(Object object) {
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

    private static final String INSERT_STATEMENT = "insert into {0} ({1}) values ({2})";




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
}
