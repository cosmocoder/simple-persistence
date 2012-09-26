package org.simplepersistence;

public interface PropertyWriter<Object> {
    void setTo(Object object, Object value);
}
