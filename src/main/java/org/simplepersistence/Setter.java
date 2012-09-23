package org.simplepersistence;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Throwables.propagate;

public class Setter<O, F> implements MemberWriter<O,F> {

    private final Method setter;
    private transient final Class<F> memberType;
    private transient final Class<O> objectType;

    public Setter(Method setter, Class<O> objectType, Class<F> memberType) {
        Class<?>[] setterParameterTypes = setter.getParameterTypes();
        checkArgument(
                setterParameterTypes.length == 1,
                "Setter method must have only one parameter"
        );

        checkArgument(
                setterParameterTypes[0].isAssignableFrom(memberType),
                "Member type must be assignable from the type of setter method parameter"
        );

        checkArgument(
                setter.getDeclaringClass().isAssignableFrom(objectType),
                "Type of argument object  must be assignable from the declaring class of argument field"
        );
        this.setter = setter;
        this.memberType = memberType;
        this.objectType = objectType;
        setter.setAccessible(true);
    }

    @Override
    public void setTo(O object, F value) {
        try {
            setter.invoke(object, value);
        } catch (IllegalAccessException e) {
            throw propagate(e);
        } catch (InvocationTargetException e) {
            throw propagate(e);
        }
    }

    @Override
    public Class<F> getMemberType() {
        return memberType;
    }

    @Override
    public Class<O> getObjectType() {
        return objectType;
    }

    public static <O,F> Setter<O, F> create(Method getter, Class<O> objectType, Class<F> fieldType) {
        return new Setter(getter, objectType, fieldType);
    }

}
