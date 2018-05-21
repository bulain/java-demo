package com.bulain.hibernate.core;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.proxy.HibernateProxy;

import com.bulain.common.exception.NotFoundException;
import com.bulain.common.exception.ServiceException;
import com.bulain.hibernate.util.ReflectionUtils;

public abstract class BasicServiceImpl<T> implements BasicService<T> {
    protected abstract BasicMapper<T> getBasicMapper();
    
    public T get(Long id, List<String> properties) {
        T entity = getBasicMapper().selectByPrimaryKey(id);
        if (entity == null) {
            throw new NotFoundException();
        }
        
        initialize(entity, properties);
        
        return entity;
    }
    
    @SuppressWarnings("rawtypes")
    private void initialize(Object entity, List<String> properties) {
        if (!Hibernate.isInitialized(entity)) {
            Hibernate.initialize(entity);
        }
        
        if (entity instanceof Collection) {
            for (Object obj : (Collection) entity) {
                initialize(obj, properties);
            }
            return;
        }
        
        if (properties == null) {
            return;
        }
        
        for (String propertyName : properties) {
            String[] splits = propertyName.split("\\.");
            Iterator<String> iterator = Arrays.asList(splits).iterator();
            initializeOne(entity, iterator);
        }
    }
    
    @SuppressWarnings("rawtypes")
    private void initializeOne(Object entity, Iterator<String> iterator) {
        Object target = entity;
        while(iterator.hasNext()){
            if (target instanceof Collection) {
                for (Object obj : (Collection) target) {
                    initializeOne(obj, iterator);
                }
                return;
            }
            
            String split = iterator.next();
            if (target instanceof HibernateProxy) {
                target = ((HibernateProxy) target).getHibernateLazyInitializer().getImplementation();
            }
            Object proxy = ReflectionUtils.getFieldValue(target, split);
            if (!Hibernate.isInitialized(proxy)) {
                Hibernate.initialize(proxy);
            }
            target = proxy;
        }
    }
    
    public void insert(T record) {
        int count = getBasicMapper().insert(record);
        if (count != 1) {
            throw new ServiceException();
        }
    }
    
    public void update(T record) {
        int count = 0;
        count = getBasicMapper().updateByPrimaryKey(record);
        if (count != 1) {
            throw new ServiceException();
        }
    }
    
    public void delete(Long id) {
        int count = getBasicMapper().deleteByPrimaryKey(id);
        if (count != 1) {
            throw new ServiceException();
        }
    }
}
