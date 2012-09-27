package org.simplepersistence.model.types;

import static com.google.common.collect.Maps.newHashMap;

public class RelationshipType {
    private final String name;
    private final AssociationType firstRole;
    private final AssociationType secondRole;

    public RelationshipType(String name, final AssociationType firstRole, final AssociationType secondRole) {
        this.name = name;
        this.firstRole = firstRole;
        this.firstRole.setRelationship(this);
        this.secondRole = secondRole;
        this.secondRole.setRelationship(this);
    }

    public String getName() {
        return name;
    }

    public AssociationType getFirstRole() {
        return firstRole;
    }

    public AssociationType getSecondRole() {
        return secondRole;
    }
}
