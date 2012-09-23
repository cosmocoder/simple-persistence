package org.simplepersistence;

public interface MemberWriter<O,M> {
    void setTo(O object, M value);
    Class<M> getMemberType();
    Class<O> getObjectType();
}
