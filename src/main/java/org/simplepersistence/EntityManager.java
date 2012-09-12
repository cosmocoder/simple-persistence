package org.simplepersistence;

/**
 * Created with IntelliJ IDEA.
 * User: rodrigo.guerra1
 * Date: 11/09/12
 * Time: 20:39
 * To change this template use File | Settings | File Templates.
 */
public interface EntityManager {
    void insert(Object object);

    void update(Object object);

    void delete(Object object);

    void findAll(Class clazz);
}
