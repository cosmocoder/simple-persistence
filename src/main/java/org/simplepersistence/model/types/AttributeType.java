package org.simplepersistence.model.types;

import org.simplepersistence.PropertyAccessor;

public class AttributeType implements PropertyType {
    private final String name;
    private final PropertyAccessor accessor;
    private final EntityType entityStructure;

    AttributeType(EntityType owner, String name, PropertyAccessor accessor) {
        this.entityStructure = owner;
        this.name = name;
        this.accessor = accessor;
    }

    @Override
    public String getName() {
        return name;
    }

    public PropertyAccessor getAccessor() {
        return accessor;
    }

    public EntityType getEntityStructure() {
        return entityStructure;
    }
}
