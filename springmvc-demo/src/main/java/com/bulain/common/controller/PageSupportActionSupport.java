package com.bulain.common.controller;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.bulain.common.page.OrderBy;
import com.bulain.common.page.Page;
import com.bulain.common.page.Search;
import com.bulain.common.util.KeyUtil;

public abstract class PageSupportActionSupport {
    private static final Logger LOG = LoggerFactory.getLogger(PageSupportActionSupport.class);

    protected Page getPageFromSession(Page page, HttpSession session) {
        if (page.getPage() == 0) {
            page = getBeanFromSession(Page.class, session);
        }
        return page;
    }
    
    protected OrderBy getOrderByFromSession(OrderBy orderBy, HttpSession session) {
        if (!StringUtils.hasText(orderBy.getOrderBy())) {
            orderBy = getBeanFromSession(OrderBy.class, session);
        }
        return orderBy;
    }

    protected <T extends Search> T getSearchFromSession(Class<T> clz, HttpSession session) {
        return getBeanFromSession(clz, session);
    }

    @SuppressWarnings("unchecked")
    protected <T> T getBeanFromSession(Class<T> clz, HttpSession session) {
        String key = KeyUtil.getSessionKey(this.getClass(), clz);

        Object bean = null;
        if (bean == null) {
            bean = session.getAttribute(key);
        }
        if (bean == null) {
            try {
                Constructor<T> constructor = clz.getConstructor();
                bean = constructor.newInstance();
            } catch (SecurityException e) {
                LOG.error("getBeanFromSession(Class)", e);
            } catch (NoSuchMethodException e) {
                LOG.error("getBeanFromSession(Class)", e);
            } catch (IllegalArgumentException e) {
                LOG.error("getBeanFromSession(Class)", e);
            } catch (InstantiationException e) {
                LOG.error("getBeanFromSession(Class)", e);
            } catch (IllegalAccessException e) {
                LOG.error("getBeanFromSession(Class)", e);
            } catch (InvocationTargetException e) {
                LOG.error("getBeanFromSession(Class)", e);
            }
        }

        return (T) bean;
    }

    protected <T> void putBeanToSession(T bean, HttpSession session) {
        String key = KeyUtil.getSessionKey(this.getClass(), bean.getClass());
        session.setAttribute(key, bean);
    }

}
