package org.simplepersistence;

import static com.google.common.base.Preconditions.checkArgument;

public class DefaultMemberAccessor<O, M> implements MemberReader<O,M>, MemberWriter<O,M> {
    private final MemberReader<O, M> memberReader;
    private final MemberWriter<O, M> memberWriter;
    private transient final Class<M> memberType;
    private transient final Class<O> objectType;

    public DefaultMemberAccessor(MemberReader<O, M> memberReader, MemberWriter<O, M> memberWriter) {
        Class<M> memberWriterType = memberWriter.getMemberType();
        checkArgument(
                memberReader.getMemberType().equals(memberWriterType),
                "Member reader type must match member writer type"
        );

        Class<O> readerObjectType = memberReader.getObjectType();
        Class<O> writerObjectType = memberWriter.getObjectType();
        checkArgument(
                readerObjectType.isAssignableFrom(writerObjectType) ||
                        writerObjectType.isAssignableFrom(readerObjectType),
                "Object type of reader and writer must match"
        );

        this.memberReader = memberReader;
        this.memberWriter = memberWriter;
        this.memberType = memberWriterType;
        this.objectType = writerObjectType;
    }

    @Override
    public M getFrom(O object) {
        return memberReader.getFrom(object);
    }

    @Override
    public void setTo(O object, M value) {
        memberWriter.setTo(object, value);
    }

    @Override
    public Class<M> getMemberType() {
        return memberType;
    }

    @Override
    public Class<O> getObjectType() {
        return objectType;
    }

    public static <O, M> DefaultMemberAccessor<O, M> create(MemberReader<O, M> memberReader, MemberWriter<O, M> memberWriter) {
        return new DefaultMemberAccessor<O,M>(memberReader, memberWriter);
    }
}
