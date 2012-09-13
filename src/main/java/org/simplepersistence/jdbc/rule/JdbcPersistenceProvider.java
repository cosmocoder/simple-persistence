package org.simplepersistence.jdbc.rule;

import org.simplepersistence.DefaultObjectManager;
import org.simplepersistence.ObjectManager;
import org.simplepersistence.PersistenceProvider;
import org.simplepersistence.SchemaManager;
import org.simplepersistence.jdbc.JdbcMarshaller;
import org.simplepersistence.jdbc.JdbcSchemaManager;

import static org.simplepersistence.TemporaryDatabaseStuff.newConnection;

public class JdbcPersistenceProvider implements PersistenceProvider {

    public ObjectManager getObjectManager() {
        return new DefaultObjectManager(new JdbcMarshaller(newConnection(), new UnderscoreUpperCaseNamingRule(), new UnderscoreUpperCaseNamingRule()));
    }

    public SchemaManager getSchemaManager() {
        return new JdbcSchemaManager(newConnection(), new UnderscoreUpperCaseNamingRule(), new UnderscoreUpperCaseNamingRule());
    }
}
