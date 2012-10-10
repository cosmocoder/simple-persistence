package org.simplepersistence.model.types;

import com.google.common.collect.ImmutableSet;

import java.util.Map;
import java.util.Set;

import static com.google.common.collect.Maps.newHashMap;

public class AssociationType {
    private Map<String, AssociationMemberType> members = newHashMap();
    private final EntityType entity;
    private final String name;

    public AssociationType(EntityType entity, String name) {
        this.entity = entity;
        this.name = name;
    }

    void addMember(AssociationMemberType member) {
        this.members.put(member.getName(), member);
    }

    public Set<AssociationMemberType> getMembers() {
        return ImmutableSet.copyOf(members.values());
    }
}
