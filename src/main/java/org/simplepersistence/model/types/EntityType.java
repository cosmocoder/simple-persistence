package org.simplepersistence.model.types;

import com.google.common.collect.ImmutableSet;

import java.util.Map;
import java.util.Set;

import static com.google.common.collect.Maps.newHashMap;
import static com.google.common.collect.Sets.newHashSet;

public class EntityType {
    private final Class clazz;
    private Map<String, AttributeType> attributes = newHashMap();
    private Map<String, AssociationMemberType> associations = newHashMap();
    private Set<PropertyType> identifier = newHashSet();

    public EntityType(Class clazz) {
        this.clazz = clazz;
    }

    void addAttribute(AttributeType attribute) {
        this.attributes.put(attribute.getName(), attribute);
    }

    void addAssociation(AssociationMemberType role) {
        this.associations.put(role.getName(), role);
    }

    void addProperty(PropertyType property) {
        if (property instanceof AttributeType) {
            addAttribute((AttributeType) property);
        } else {
            addAssociation((AssociationMemberType) property);
        }
    }

    public Class getClazz() {
        return clazz;
    }

    public AttributeType getAttribute(String name) {
        return attributes.get(name);
    }

    public AssociationMemberType getAssociation(String name) {
        return associations.get(name);
    }

    public Set<PropertyType> getIdentifier() {
        return ImmutableSet.copyOf(identifier);
    }

    public Set<AttributeType> getAttributes() {
        return ImmutableSet.copyOf(attributes.values());
    }

    public Set<AssociationMemberType> getAssociations() {
        return ImmutableSet.copyOf(associations.values());
    }

}
