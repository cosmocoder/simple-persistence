package org.simplepersistence.model.types;

import org.simplepersistence.PropertyAccessor;

public class AssociationMemberType implements PropertyType {
    private final String name;
    private final Cardinality cardinality;
    private final PropertyAccessor accessor;
    private final EntityType entity;
    private final AssociationType association;

    public AssociationMemberType(String name, EntityType entity, AssociationType association, Cardinality cardinality, PropertyAccessor accessor) {
        this.accessor = accessor;
        this.name = name;
        this.cardinality = cardinality;
        this.entity = entity;
        this.association = association;
        this.entity.addAssociation(this);
        this.association.addMember(this);
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
