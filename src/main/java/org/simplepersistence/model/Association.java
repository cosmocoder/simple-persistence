package org.simplepersistence.model;

import org.simplepersistence.model.types.AssociationType;

public class Association {
    private final AssociationType type;
    private final String name;
    private final Entity entity;

    public Association(AssociationType type, String name, Entity entity) {
        this.type = type;
        this.name = name;
        this.entity = entity;
    }
}
