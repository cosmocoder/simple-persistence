package org.simplepersistence.erm;

import org.simplepersistence.erm.structure.EntityStructure;
import org.simplepersistence.erm.structure.RelationshipPartStructure;
import org.simplepersistence.erm.structure.RelationshipStructure;

public class Entity {
    private final EntityStructure structure;

    public Entity(EntityStructure structure) {
        this.structure = structure;
    }

    public Object getAttribute(String name) {
        return structure.getAttribute(name).getAccessor().getFrom(this);
    }

    public void setAttribute(String name, Object value) {
        structure.getAttribute(name).getAccessor().setTo(this,value);
    }

}
