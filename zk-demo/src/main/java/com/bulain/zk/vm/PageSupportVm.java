package com.bulain.zk.vm;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zkoss.util.Locales;

import com.bulain.common.page.Page;
import com.bulain.common.page.Search;

public abstract class PageSupportVm implements Serializable {
    private static final long serialVersionUID = 1202195849117150279L;
    private static final Logger LOG = LoggerFactory.getLogger(PageSupportVm.class);

    protected Page page;

    protected Search prepareSearch(Class<?> clz, Search search) {
        if (search == null) {
            search = (Search) newInstance(clz);
        }
        if (page == null) {
            page = new Page();
        }
        return search;
    }

    public int getActivePage() {
        return page.getPage();
    }

    protected Object newInstance(Class<?> clz) {
        Object bean = null;
        try {
            Constructor<?> constructor = clz.getConstructor();
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
        return bean;
    }

    protected String getLanguage() {
        return Locales.getCurrent().getLanguage();
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

}
