package org.simplepersistence.erm.structure;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

import java.util.Map;
import java.util.Set;

public class EntityStructure {
    private final Class clazz;
    private Map<String, AttributeStructure> attributes;
    private Map<String, RelationshipStructure> relationships;
    private Set<MemberStructure> identifierMembers;

    public EntityStructure(Class clazz) {
        this.clazz = clazz;
    }

    void addAttribute(AttributeStructure attribute) {
        this.attributes.put(attribute.getName(), attribute);
    }
    
    void addRelationship(RelationshipStructure relationship) {
        this.relationships.put(relationship.getName(), relationship);
    }

    public Class getClazz() {
        return clazz;
    }

    public AttributeStructure getAttribute(String name) {
        return attributes.get(name);
    }

    public Map<String, RelationshipStructure> getRelationships() {
        return ImmutableMap.copyOf(relationships);
    }

    public Set<MemberStructure> getIdentifierMembers() {
        return ImmutableSet.copyOf(identifierMembers);
    }
}
