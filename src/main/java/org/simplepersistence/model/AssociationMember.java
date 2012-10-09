package org.simplepersistence.model;

import org.simplepersistence.model.types.AssociationMemberType;

public class AssociationMember implements Property {
    private final AssociationMemberType type;
    private final Entity value;

    public AssociationMember(final AssociationMemberType type, final Entity entity) {
        this.type = type;
        this.value = entity;
    }

    @Override
    public AssociationMemberType getType() {
        return type;
    }

    @Override
    public Entity getValue() {
        return value;
    }
}
