package org.simplepersistence.erm.structure;

import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;

public class RelationshipStructure implements MemberStructure {
    private final String name;
    private Map<String,RelationshipPartStructure> parts = newHashMap();

    public RelationshipStructure(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    void addPart(RelationshipPartStructure part) {
        parts.put(part.getRole(), part);
    }
}
