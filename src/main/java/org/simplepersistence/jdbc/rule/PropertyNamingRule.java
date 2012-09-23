package org.simplepersistence.jdbc.rule;

import java.lang.reflect.Field;

public interface PropertyNamingRule {
    String apply(Field field);
}
