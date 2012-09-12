package org.simplepersistence.naming;

import com.google.common.base.Joiner;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;

import static org.apache.commons.lang3.StringUtils.splitByCharacterTypeCamelCase;

public class UnderscoreUpperCaseNamingRule implements EntityNamingRule, PropertyNamingRule{
    public String apply(final Class clazz) {
        return apply(clazz.getSimpleName());
    }

    public String apply(final Field field) {
        return apply(field.getName());
    }

    public String apply(final String name) {
        return Joiner.on("_").join(splitByCharacterTypeCamelCase(name)).toUpperCase();
    }
}
