package org.simplepersistence.jdbc;

import org.simplepersistence.SchemaManager;
import org.simplepersistence.jdbc.rule.EntityNamingRule;
import org.simplepersistence.jdbc.rule.PropertyNamingRule;
import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableMap;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

import static org.simplepersistence.TemporaryDatabaseStuff.executeAtDB;
import static com.google.common.collect.Lists.newArrayList;
import static java.text.MessageFormat.format;

public class JdbcSchemaManager implements SchemaManager {

    private final Connection connection;
    private final EntityNamingRule entityNamingRule;
    private final PropertyNamingRule propertyNamingRule;
    private static final String CREATE_STATEMENT = "create table {0} ({1})";
    private static final String COLUMN_DESCRIPTION = "{0} {1}";


    public JdbcSchemaManager(final Connection connection, final EntityNamingRule entityNamingRule, final PropertyNamingRule propertyNamingRule) {
        this.connection = connection;
        this.entityNamingRule = entityNamingRule;
        this.propertyNamingRule = propertyNamingRule;
    }

    public void createModel(Class... entityClasses) {
        for (Class entityClass : entityClasses) {
            executeAtDB(connection, createStatement(entityClass));
        }
    }

    public void createModelWithDependencies(Class... entityClasses) {
        for (Class entityClass : entityClasses) {
            executeAtDB(connection, createStatement(entityClass));
        }
    }

    private String createStatement(final Class clazz) {
        String tableName = entityNamingRule.apply(clazz);
        List<String> columnDescriptions = newArrayList();
        for (Field field : clazz.getDeclaredFields()) {
            columnDescriptions.add(format(COLUMN_DESCRIPTION, propertyNamingRule.apply(field), sqlType(field)));
        }
        return format(CREATE_STATEMENT, tableName, Joiner.on(", ").join(columnDescriptions));
    }


    private static Map<String, String> TYPES =
            ImmutableMap.of(
                    "id", "INT",
                    "name", "VARCHAR(50)",
                    "nameOfMother", "VARCHAR(50)"
            );

    private String sqlType(Field field) {
        return TYPES.get(field.getName());
    }
}
