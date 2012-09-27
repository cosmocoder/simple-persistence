package org.simplepersistence.model.types;

import org.simplepersistence.PropertyAccessor;

public interface PropertyType {
   String getName();
   PropertyAccessor getAccessor();
}
