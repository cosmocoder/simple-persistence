package org.simplepersistence.model.types;

import com.google.common.collect.ImmutableSet;

import java.util.Map;
import java.util.Set;

import static com.google.common.collect.Maps.newHashMap;
import static com.google.common.collect.Sets.newHashSet;

public class EntityType {
    private final Class clazz;
    private Map<String, AttributeType> attributes = newHashMap();
    private Map<String, AssociationType> relationshipRoles = newHashMap();
    private Set<PropertyType> identifierMembers = newHashSet();

    public EntityType(Class clazz) {
        this.clazz = clazz;
    }

    void addAttribute(AttributeType attribute) {
        this.attributes.put(attribute.getName(), attribute);
    }
    
    void addAssociation(AssociationType role) {
        this.relationshipRoles.put(role.getName(), role);
    }

    public Class getClazz() {
        return clazz;
    }

    public AttributeType getAttribute(String name) {
        return attributes.get(name);
    }

    public AssociationType getAssociation(String name) {
        return relationshipRoles.get(name);
    }

    public Set<PropertyType> getIdentifierMembers() {
        return ImmutableSet.copyOf(identifierMembers);
    }
}
