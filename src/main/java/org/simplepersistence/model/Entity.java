package org.simplepersistence.model;

import com.google.common.base.Objects;
import org.simplepersistence.model.types.EntityType;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Sets.newHashSet;

public class Entity {
    private final EntityType type;
    private final Set<Property> identifierProperties = newHashSet();
    private final Map<String,Attribute> attributes;

    public Entity(final EntityType type,
                  final Set<Property> identifier,
                  final Map<String, Attribute> attributes) {
        this.type = type;
        this.attributes = attributes;
    }

    void addIdentifierPropety(Property property) {
        this.identifierProperties.add(property);
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
