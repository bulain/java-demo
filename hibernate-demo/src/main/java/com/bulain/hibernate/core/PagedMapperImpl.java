package com.bulain.hibernate.core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.impl.CriteriaImpl;
import org.hibernate.transform.ResultTransformer;

import com.bulain.common.exception.ServiceException;
import com.bulain.common.page.OrderBy;
import com.bulain.common.page.Page;
import com.bulain.common.page.Search;
import com.bulain.hibernate.util.FreemarkerUtil;
import com.bulain.hibernate.util.ReflectionUtils;

import freemarker.template.TemplateException;

@SuppressWarnings("unchecked")
public class PagedMapperImpl<T, S extends Search> extends BasicMapperImpl<T> implements PagedMapper<T, S> {
    private static final String ORDER_DESC = "desc";
    
    public PagedMapperImpl() {
        super();
    }
    
    public PagedMapperImpl(Class<T> clazz) {
        super(clazz);
    }
    
    public List<T> find(String queryName, S search) {
        List<T> list = Collections.emptyList();
        try {
            String fullQueryName = getQueryName(queryName);
            Query namedQuery = getSession().getNamedQuery(fullQueryName);
            String queryString = namedQuery.getQueryString();
            String hql = FreemarkerUtil.formatHQL(queryString, search);
            Query query = getSession().createQuery(hql);
            query.setProperties(search);
            list = query.list();
        } catch (IOException e) {
            throw new ServiceException(e);
        } catch (TemplateException e) {
            throw new ServiceException(e);
        }
        return list;
    }

    public Long count(String queryName, S search) {
        long totalCount = 0L;
        try {
            String fullQueryName = getQueryName(queryName);
            Query namedQuery = getSession().getNamedQuery(fullQueryName);
            String queryString = namedQuery.getQueryString();
            String tempHql = FreemarkerUtil.formatHQL(queryString, search);
            
            //remove projection
            int indexOfFrom = tempHql.toLowerCase().indexOf("from");
            int length = tempHql.length();
            StringBuffer hql = new StringBuffer();
            hql.append("select count(*) ");
            hql.append(tempHql.substring(indexOfFrom, length));
            
            Query query = getSession().createQuery(hql.toString());
            query.setProperties(search);
            Number cnt = (Number) query.uniqueResult();
            totalCount = cnt.longValue();
        } catch (IOException e) {
            throw new ServiceException(e);
        } catch (TemplateException e) {
            throw new ServiceException(e);
        }
        return totalCount;
    }
    
    public List<T> page(String queryName, S search) {
        List<T> list = Collections.emptyList();
        try {
            String fullQueryName = getQueryName(queryName);
            Query namedQuery = getSession().getNamedQuery(fullQueryName);
            String queryString = namedQuery.getQueryString();
            String hql = FreemarkerUtil.formatHQL(queryString, search);
            Query query = getSession().createQuery(hql);
            query.setFirstResult((int) search.getLow());
            query.setMaxResults((int) search.getPageSize());
            query.setProperties(search);
            list = query.list();
        } catch (IOException e) {
            throw new ServiceException(e);
        } catch (TemplateException e) {
            throw new ServiceException(e);
        }
        return list;
    }
    
    private String getQueryName(String queryName) {
        return entityClass.getName() + "." + queryName;
    }
    
    public List<T> find(DetachedCriteria dc, OrderBy orderBy) {
        if (ORDER_DESC.equals(orderBy.getSequance())) {
            dc.addOrder(Order.desc(orderBy.getOrderBy()));
        } else {
            dc.addOrder(Order.asc(orderBy.getOrderBy()));
        }
        Criteria criteria = dc.getExecutableCriteria(getSession());
        return criteria.list();
    }
    
    public Long count(DetachedCriteria dc) {
        Criteria criteria = dc.getExecutableCriteria(getSession());
        
        CriteriaImpl impl = (CriteriaImpl) criteria;
        
        // backup Projection, ResultTransformer, OrderBy
        Projection projection = impl.getProjection();
        ResultTransformer transformer = impl.getResultTransformer();
        List<CriteriaImpl.OrderEntry> orderEntries = (List<CriteriaImpl.OrderEntry>) ReflectionUtils.getFieldValue(
                impl, "orderEntries");
        ReflectionUtils.setFieldValue(impl, "orderEntries", new ArrayList<CriteriaImpl.OrderEntry>());
        
        // count
        Number cnt = (Number) criteria.setProjection(Projections.rowCount()).uniqueResult();
        long totalCount = cnt.longValue();
        
        // restore Projection, ResultTransformer, OrderBy
        criteria.setProjection(projection);
        if (projection == null) {
            criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
        }
        if (transformer != null) {
            criteria.setResultTransformer(transformer);
        }
        ReflectionUtils.setFieldValue(impl, "orderEntries", orderEntries);
        
        return totalCount;
    }
    
    public List<T> page(DetachedCriteria dc, Page page, OrderBy orderBy) {
        // add page info and order info
        if (ORDER_DESC.equals(orderBy.getSequance())) {
            dc.addOrder(Order.desc(orderBy.getOrderBy()));
        } else {
            dc.addOrder(Order.asc(orderBy.getOrderBy()));
        }
        Criteria criteria = dc.getExecutableCriteria(getSession());
        criteria.setFirstResult((int) page.getLow());
        criteria.setMaxResults((int) page.getPageSize());
        
        return criteria.list();
    }
    
    
}
