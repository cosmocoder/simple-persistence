package org.simplepersistence;

public interface SchemaManager {
    void createModel(Class... entityClasses);
    void createModelWithDependencies(Class... entityClasses);
}
