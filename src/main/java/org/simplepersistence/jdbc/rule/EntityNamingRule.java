package org.simplepersistence.jdbc.rule;

public interface EntityNamingRule {
    String apply(Class clazz);
}
