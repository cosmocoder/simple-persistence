package org.simplepersistence;

public interface PropertyWriter<Object> {
    void setValueToObject(Object object, Object value);
}
