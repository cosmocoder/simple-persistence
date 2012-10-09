package org.simplepersistence.model.types;

import org.simplepersistence.PropertyAccessor;

public class AssociationMemberType implements PropertyType {
    private final String name;
    private final Cardinality cardinality;
    private final PropertyAccessor accessor;
    private final EntityType entity;

    public AssociationMemberType(String name, EntityType entity, Cardinality cardinality, PropertyAccessor accessor) {
        this.accessor = accessor;
        this.name = name;
        this.cardinality = cardinality;
        this.entity = entity;
        this.entity.addAssociation(this);
    }

    public String getName() {
        return name;
    }

    public Cardinality getCardinality() {
        return cardinality;
    }

    public PropertyAccessor getAccessor() {
        return accessor;
    }

}
