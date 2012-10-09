package org.simplepersistence.model;

import com.google.common.base.*;
import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.simplepersistence.model.types.EntityType;
import org.simplepersistence.model.types.PropertyType;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import static com.google.common.base.Predicates.*;
import static com.google.common.collect.Collections2.transform;
import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.*;
import static com.google.common.collect.Sets.filter;
import static com.google.common.collect.Sets.newHashSet;

public class Entity {
    private final EntityType type;
    private Set<Property> identifierProperties;
    private final Map<String,Attribute> attributes;
    private final Map<String,AssociationMember> associations;

    public Entity(final EntityType type, Map<String,Property> properties) {
        this.type = type;
        this.attributes = filterProperties(properties, Attribute.class);
        this.associations = filterProperties(properties, AssociationMember.class);
        this.identifierProperties = newHashSet(Iterables.filter(properties.values(), compose(Predicates.in(type.getIdentifier()), getTypeFunction())));
    }

    private static Function<Property, PropertyType> getTypeFunction() {
        return null;  //To change body of created methods use File | Settings | File Templates.
    }

    private <T extends Property> Map<String, T> filterProperties(final Map<String, Property> properties, final Class<T> clazz) {
        return (Map<String,T>) filterValues(properties, instanceOf(clazz));
    }

    private Entity(final EntityType type,
                  final Set<Property> identifierProperties,
                  final Map<String, Attribute> attributes,
                  final Map<String, AssociationMember> associations) {
        this.type = type;
        this.attributes = attributes;
        this.associations = associations;
    }

    public EntityType getType() {
        return type;
    }

    public Set<Property> getIdentifierProperties() {
        return identifierProperties;
    }

    public Attribute getAttribute(String name) {
        return attributes.get(name);
    }

    public Collection<Attribute> getAttributes() {
        return attributes.values();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(identifierProperties);
    }

    @Override
    public boolean equals(final Object object) {
        if (object instanceof Entity) {
            final Entity other = (Entity) object;
            return Objects.equal(identifierProperties, other.identifierProperties);
        } else {
            return false;
        }
    }
}
