package org.simplepersistence.model.structure;

import org.simplepersistence.PropertyAccessor;

public class AttributeStructure implements PropertyStructure {
    private final String name;
    private final PropertyAccessor accessor;
    private final EntityStructure entityStructure;

    AttributeStructure(EntityStructure owner, String name, PropertyAccessor accessor) {
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

    public EntityStructure getEntityStructure() {
        return entityStructure;
    }
}
