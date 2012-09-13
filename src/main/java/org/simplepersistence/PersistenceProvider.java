package org.simplepersistence;

public interface PersistenceProvider {
    ObjectManager getObjectManager();
    SchemaManager getSchemaManager();
}
