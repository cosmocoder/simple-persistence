package org.simplepersistence.naming;

public interface EntityNamingRule {
    String apply(Class clazz);
}
