package org.simplepersistence.model;

import org.simplepersistence.model.types.AttributeType;

public class Attribute implements Property {
    private final AttributeType type;
    private final Object value;

    public Attribute(AttributeType type, Object value) {
        this.type = type;
        this.value = value;
    }

    @Override
    public AttributeType getType() {
        return type;
    }

    @Override
    public Object getValue() {
        return value;
    }
}
