package org.simplepersistence.model;

import org.simplepersistence.model.structure.EntityStructure;
import org.simplepersistence.model.structure.PropertyStructure;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Sets.newHashSet;

public class Entity {
    private final EntityStructure structure;
    private final Object object;
    private final LogicalContext context;

    public Entity(EntityStructure structure, final Object object, LogicalContext context) {
        this.structure = structure;
        this.object = object;
        this.context = context;
    }

    public Object getAttribute(String name) {
        return structure.getAttribute(name).getAccessor().getFrom(object);
    }

    public void setAttribute(String name, Object value) {
        structure.getAttribute(name).getAccessor().setTo(object, value);
    }

    public Collection<Entity> getRelatedEntities(String name) {
        Object related = structure.getRelationshipRole(name).getAccessor().getFrom(object);
        if(related instanceof Iterable) {
            return context.getEntities((Iterable) related);
        } //TODO: ARRAYS
        return newHashSet(context.getEntity(related));
    }

    public Object getIdentifier() {
        Object[] identifier = getIdentifierArray();
        return identifier.length == 1 ? identifier[0] : identifier;
    }

    private Object[] getIdentifierArray() {
        List<Object> identifier = newArrayList();
        final Set<PropertyStructure> identifierMembers = structure.getIdentifierMembers();
        for (PropertyStructure property : identifierMembers) {
            identifier.add(property.getAccessor().getFrom(object));
        }
        return identifier.toArray();
    }


    public Object getObject() {
        return object;
    }
}
