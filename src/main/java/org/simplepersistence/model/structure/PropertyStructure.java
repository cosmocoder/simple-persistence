package org.simplepersistence.model.structure;

import org.simplepersistence.PropertyAccessor;

public interface PropertyStructure {
   String getName();
   PropertyAccessor getAccessor();
}
