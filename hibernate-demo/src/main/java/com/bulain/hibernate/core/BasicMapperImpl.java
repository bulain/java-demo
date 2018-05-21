package com.bulain.hibernate.core;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bulain.hibernate.util.ReflectionUtils;

public class BasicMapperImpl<T> extends HibernateDaoSupport implements BasicMapper<T> {

    protected Class<T> entityClass;

    public BasicMapperImpl(){
        this.entityClass = ReflectionUtils.getSuperClassGenricType(getClass());
    }
    
    public BasicMapperImpl(Class<T> clazz){
        this.entityClass = clazz;
    }

    public int deleteByPrimaryKey(Long id) {
        Object entity = getSession().get(entityClass, id);
        getSession().delete(entity);
        return 1;
    }

    public int insert(T record) {
        getSession().save(record);
        return 1;
    }

    @SuppressWarnings("unchecked")
    public T selectByPrimaryKey(Long id) {
        return (T) getSession().get(entityClass, id);
    }

    public int updateByPrimaryKey(T record) {
        getSession().update(record);
        return 1;
    }
}
