package org.simplepersistence.erm.structure;

import org.simplepersistence.MemberAccessor;

public class AttributeStructure implements MemberStructure{
    private final String name;
    private final MemberAccessor accessor;
    private final EntityStructure entityStructure;

    public AttributeStructure(EntityStructure owner, String name, MemberAccessor accessor) {
        this.entityStructure = owner;
        this.name = name;
        this.accessor = accessor;
        owner.addAttribute(this);
    }

    @Override
    public String getName() {
        return name;
    }

    public MemberAccessor getAccessor() {
        return accessor;
    }

    public EntityStructure getEntityStructure() {
        return entityStructure;
    }
}
