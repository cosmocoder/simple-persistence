package org.simplepersistence;

import static com.google.common.collect.Maps.newHashMap;
import static com.google.common.collect.Maps.newLinkedHashMap;

public class DefaultObjectManager implements ObjectManager {

    private final PhysicalMarshaller marshaller;

    public DefaultObjectManager(final PhysicalMarshaller marshaller) {
        this.marshaller = marshaller;
    }

    public void save(final Object object) {
        marshaller.save(object);
    }
}
