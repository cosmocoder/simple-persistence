package org.simplepersistence;


import java.lang.reflect.Field;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Throwables.propagate;

public class FieldAccessor<O, F> implements MemberAccessor<O, F> {

    private final Field field;
    private transient final Class<F> fieldType;
    private transient final Class<O> objectType;

    public FieldAccessor(Field field, Class<O> objectType, Class<F> fieldType) {
        checkArgument(
                field.getType().equals(fieldType),
                "Field type must match type of argument field"
        );

        checkArgument(
                field.getDeclaringClass().isAssignableFrom(objectType),
                "Declaring class of field must be assignable from type of argument object"
        );
        this.field = field;
        this.fieldType = fieldType;
        this.objectType = objectType;
        field.setAccessible(true);
    }

    @Override
    public F getFrom(O object) {
        try {
            return (F) field.get(object);
        } catch (IllegalAccessException e) {
            throw propagate(e);
        }
    }

    @Override
    public void setTo(O object, F value) {
        try {
            field.set(object, value);
        } catch (IllegalAccessException e) {
            throw propagate(e);
        }
    }

    @Override
    public Class<F> getMemberType() {
        return fieldType;
    }

    @Override
    public Class<O> getObjectType() {
        return objectType;
    }

    public static <O,F> FieldAccessor<O, F> create(Field field, Class<O> objectType, Class<F> fieldType) {
        return new FieldAccessor(field, objectType, fieldType);
    }
}
