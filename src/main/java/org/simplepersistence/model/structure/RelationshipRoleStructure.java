package org.simplepersistence.model.structure;

import org.simplepersistence.PropertyAccessor;

public class RelationshipRoleStructure implements PropertyStructure {
    private final String name;
    private final Cardinality cardinality;
    private final PropertyAccessor accessor;
    private RelationshipStructure relationship;
    private final EntityStructure entity;

    public RelationshipRoleStructure(EntityStructure ownerEntity, String roleName, Cardinality cardinality, PropertyAccessor accessor) {
        this.accessor = accessor;
        this.name = roleName;
        this.cardinality = cardinality;
        this.entity = ownerEntity;
        this.entity.addRelationshipRole(this);
    }

    void setRelationship(final RelationshipStructure relationship) {
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

    public RelationshipStructure getRelationship() {
        return relationship;
    }
}
