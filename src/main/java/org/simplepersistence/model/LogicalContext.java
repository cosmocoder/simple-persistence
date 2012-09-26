package org.simplepersistence.model;

import com.google.common.base.Predicates;
import com.google.common.collect.Maps;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import static com.google.common.collect.Maps.newHashMap;
import static com.google.common.collect.Sets.newHashSet;

public class LogicalContext {
    private Map<Object, Entity> entities = newHashMap();

    public Entity getEntity(Object object) {
        return entities.get(object);
    }

    public Collection<Entity> getEntities(Iterable<Object> objects) {
        Set<Object> objectSet = newHashSet(objects);
        return Maps.filterKeys(entities, Predicates.in(objectSet)).values();
    }

    void addEntity(Entity entity) {
        entities.put(entity.getObject(), entity);
    }
}
