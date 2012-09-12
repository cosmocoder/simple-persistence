package org.simplepersistence.utils;

import org.simplepersistence.EntityManager;
import org.simplepersistence.jdbc.JdbcSchemaManager;
import org.simplepersistence.SchemaManager;
import org.simplepersistence.jdbc.JdbcEntityManager;
import org.simplepersistence.naming.UnderscoreUpperCaseNamingRule;

import static org.simplepersistence.utils.DatabaseTestUtils.newConnection;

public class TestFactory {
    public static EntityManager newEntityManager() {
        return new JdbcEntityManager(newConnection(), new UnderscoreUpperCaseNamingRule(), new UnderscoreUpperCaseNamingRule());
    }

    public static SchemaManager newSchemaManager() {
        return new JdbcSchemaManager(newConnection(), new UnderscoreUpperCaseNamingRule(), new UnderscoreUpperCaseNamingRule());
    }
}
