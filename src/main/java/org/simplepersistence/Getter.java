package org.simplepersistence;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Throwables.propagate;

public class Getter<O,F> implements MemberReader<O,F>{

    private final Method getter;
    private transient final Class<F> fieldType;
    private transient final Class<O> objectType;

    public Getter(Method getter, Class<O> objectType, Class<F> fieldType) {
        Class<?>[] setterParameters = getter.getParameterTypes();
        checkArgument(
                setterParameters.length == 0,
                "Getter method should not have parameters"
        );

        checkArgument(
                fieldType.isAssignableFrom(getter.getReturnType()),
                "Field type must be assignable from the return type of getter method"
        );

        checkArgument(
                getter.getDeclaringClass().isAssignableFrom(objectType),
                "Declaring class of argument field must be assignable from type of argument object"
        );
        this.getter = getter;
        this.fieldType = fieldType;
        this.objectType = objectType;
        getter.setAccessible(true);
    }

    @Override
    public F getFrom(O object) {
        try {
            return (F) getter.invoke(object);
        } catch (IllegalAccessException e) {
            throw propagate(e);
        } catch (InvocationTargetException e) {
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

    public static <O,F> Getter<O, F> create(Method getter, Class<O> objectType, Class<F> fieldType) {
        return new Getter(getter, objectType, fieldType);
    }
}
