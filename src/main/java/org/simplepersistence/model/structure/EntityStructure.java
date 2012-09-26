package org.simplepersistence.model.structure;

import com.google.common.collect.ImmutableSet;

import java.util.Map;
import java.util.Set;

import static com.google.common.collect.Maps.newHashMap;
import static com.google.common.collect.Sets.newHashSet;

public class EntityStructure {
    private final Class clazz;
    private Map<String, AttributeStructure> attributes = newHashMap();
    private Map<String, RelationshipRoleStructure> relationshipRoles = newHashMap();
    private Set<PropertyStructure> identifierMembers = newHashSet();

    public EntityStructure(Class clazz) {
        this.clazz = clazz;
    }

    void addAttribute(AttributeStructure attribute) {
        this.attributes.put(attribute.getName(), attribute);
    }
    
    void addRelationshipRole(RelationshipRoleStructure role) {
        this.relationshipRoles.put(role.getName(), role);
    }

    public Class getClazz() {
        return clazz;
    }

    public AttributeStructure getAttribute(String name) {
        return attributes.get(name);
    }

    public RelationshipRoleStructure getRelationshipRole(String name) {
        return relationshipRoles.get(name);
    }

    public Set<PropertyStructure> getIdentifierMembers() {
        return ImmutableSet.copyOf(identifierMembers);
    }
}
