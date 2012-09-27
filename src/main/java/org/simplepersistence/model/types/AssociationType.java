package org.simplepersistence.model.types;

import org.simplepersistence.PropertyAccessor;

public class AssociationType implements PropertyType {
    private final String name;
    private final Cardinality cardinality;
    private final PropertyAccessor accessor;
    private RelationshipType relationship;
    private final EntityType entity;

    public AssociationType(EntityType ownerEntity, String roleName, Cardinality cardinality, PropertyAccessor accessor) {
        this.accessor = accessor;
        this.name = roleName;
        this.cardinality = cardinality;
        this.entity = ownerEntity;
        this.entity.addRelationshipRole(this);
    }

    void setRelationship(final RelationshipType relationship) {
        this.relationship = relationship;
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

    public RelationshipType getRelationship() {
        return relationship;
    }
}
