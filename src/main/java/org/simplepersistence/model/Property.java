package org.simplepersistence.model;

import org.simplepersistence.model.types.AssociationMemberType;
import org.simplepersistence.model.types.PropertyType;

public interface Property {
    PropertyType getType();
    Object getValue();
}
