package org.simplepersistence.model.structure;

import static com.google.common.collect.Maps.newHashMap;

public class RelationshipStructure {
    private final String name;
    private final RelationshipRoleStructure firstRole;
    private final RelationshipRoleStructure secondRole;

    public RelationshipStructure(String name, final RelationshipRoleStructure firstRole, final RelationshipRoleStructure secondRole) {
        this.name = name;
        this.firstRole = firstRole;
        this.firstRole.setRelationship(this);
        this.secondRole = secondRole;
        this.secondRole.setRelationship(this);
    }

    public String getName() {
        return name;
    }

    public RelationshipRoleStructure getFirstRole() {
        return firstRole;
    }

    public RelationshipRoleStructure getSecondRole() {
        return secondRole;
    }
}
