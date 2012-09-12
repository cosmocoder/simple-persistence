package org.simplepersistence.naming;

import java.lang.reflect.Field;

public interface PropertyNamingRule {
    String apply(Field field);
}
