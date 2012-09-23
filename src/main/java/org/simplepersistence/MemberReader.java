package org.simplepersistence;

public interface MemberReader<O,M> {
    M getFrom(O object);
    Class<M> getMemberType();
    Class<O> getObjectType();
}
