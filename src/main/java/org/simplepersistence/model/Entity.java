package org.simplepersistence.model;

import com.sun.deploy.association.Association;
import org.simplepersistence.model.types.AssociationType;
import org.simplepersistence.model.types.EntityType;
import org.simplepersistence.model.types.PropertyType;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Sets.newHashSet;

public class Entity {
    private final EntityType type;
    private final Object object;
    private final LogicalContext context;

    public Entity(EntityType type, final Object object, LogicalContext context) {
        this.type = type;
        this.object = object;
        this.context = context;
    }

    public Object getAttribute(String name) {
        return type.getAttribute(name).getAccessor().getValueFromObject(object);
    }

    public void setAttribute(String name, Object value) {
        type.getAttribute(name).getAccessor().setValueToObject(object, value);
    }

    public Collection<Entity> getAssociation(String name) {
        AssociationType associationType = type.getAssociation(name);
        if(associationType.getAccessor().getValueFromObject(object) instanceof Iterable) {
            return context.getEntities((Iterable) associationType);
        }
        return newHashSet(context.getEntity(associationType));
    }

    public Object getIdentifier() {
        Object[] identifier = getIdentifierArray();
        return identifier.length == 1 ? identifier[0] : identifier;
    }

    private Object[] getIdentifierArray() {
        List<Object> identifier = newArrayList();
        final Set<PropertyType> identifierMembers = type.getIdentifierMembers();
        for (PropertyType property : identifierMembers) {
            identifier.add(property.getAccessor().getValueFromObject(object));
        }
        return identifier.toArray();
    }


    public Object getObject() {
        return object;
    }
}
