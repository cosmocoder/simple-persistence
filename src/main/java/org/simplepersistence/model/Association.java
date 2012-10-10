package org.simplepersistence.model;
import com.google.common.base.Function;
import com.google.common.collect.Maps;
import org.simplepersistence.model.types.AssociationType;

import javax.annotation.Nullable;
import java.util.Map;

public class Association {

    private final AssociationType type;
    private final Map<String,AssociationMember> members;

    public Association(AssociationType type, Iterable<AssociationMember> members) {
        this.type = type;
        this.members = Maps.uniqueIndex(members,new Function<AssociationMember, String>() {
            @Override
            public String apply(AssociationMember input) {
                return input.getType().getName();
            }
        });
    }

    public AssociationMember getMember(String name) {
        return members.get(name);
    }

}
