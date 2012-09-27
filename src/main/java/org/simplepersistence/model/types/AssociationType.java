package org.simplepersistence.model.types;

import org.simplepersistence.PropertyAccessor;

public class AssociationType implements PropertyType {
    private final String name;
    private final Cardinality cardinality;
    private final PropertyAccessor accessor;
    private final EntityType entity;
    private AssociationType reverseAssociation;

    public AssociationType(String name, EntityType entity, Cardinality cardinality, PropertyAccessor accessor) {
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

    public AssociationType getReverseAssociation() {
        return reverseAssociation;
    }

    void setReverseAssociation(AssociationType reverseAssociation) {
        this.reverseAssociation = reverseAssociation;
    }
}
