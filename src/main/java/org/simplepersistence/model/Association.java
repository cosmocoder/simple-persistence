package org.simplepersistence.model;
import org.simplepersistence.model.types.AssociationMemberType;
import org.simplepersistence.model.types.AssociationType;

import java.util.Collection;
import java.util.Map;

import static java.util.Collections.singleton;

public class Association {

    private AssociationType type;
    private Map<String,AssociationMember> members;

    public Association(AssociationType type) {
        this.type = type;
    }

    public AssociationMember getMember(String name) {
        return members.get(name);
    }

}
