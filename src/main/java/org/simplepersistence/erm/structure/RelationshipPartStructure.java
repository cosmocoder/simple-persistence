package org.simplepersistence.erm.structure;

import org.simplepersistence.MemberAccessor;

public class RelationshipPartStructure {
    private final String role;
    private final Cardinality cardinality;
    private final MemberAccessor accessor;
    private final RelationshipStructure relationship;

    public RelationshipPartStructure(RelationshipStructure relationship, String role, Cardinality cardinality, MemberAccessor accessor) {
        this.relationship = relationship;
        this.accessor = accessor;
        this.role = role;
        this.cardinality = cardinality;
        this.relationship.addPart(this);
    }

    public String getRole() {
        return role;
    }

    public Cardinality getCardinality() {
        return cardinality;
    }

    public MemberAccessor getAccessor() {
        return accessor;
    }

    public RelationshipStructure getRelationship() {
        return relationship;
    }
}
