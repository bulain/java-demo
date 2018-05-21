package com.bulain.activiti.service;

import java.util.List;

import com.bulain.activiti.dao.ReferanceMapper;
import com.bulain.activiti.model.Referance;
import com.bulain.activiti.pojo.Constants;
import com.bulain.activiti.pojo.Item;
import com.bulain.activiti.pojo.ReferanceBean;
import com.bulain.activiti.pojo.ReferanceSearch;
import com.bulain.common.cache.CacheService;
import com.bulain.common.dao.PagedMapper;
import com.bulain.common.service.PagedServiceImpl;

public class ReferanceServiceImpl extends PagedServiceImpl<Referance, ReferanceSearch> implements ReferanceService {
    private static final String FORMAT_3 = "%s__%s__%s";
    private static final String FORMAT_4 = "%s__%s__%s__%s";

    private static final String DEFAULT_CATEGORY = "";
    private static final String DEFAULT_TEXT = "";

    private ReferanceMapper referanceMapper;
    private CacheService cacheService;

    @Override
    protected PagedMapper<Referance, ReferanceSearch> getPagedMapper() {
        return referanceMapper;
    }

    public Referance get(Long id) {
        Object object = cacheService.get(Referance.class, id);
        if (object == null) {
            object = super.get(id);
            cacheService.add(Referance.class, id, object);
        }
        return (Referance) object;
    }
    public void insert(Referance referance) {
        super.insert(referance);

        String key = String.format(FORMAT_3, referance.getName(), referance.getLang(), referance.getCategory());
        cacheService.delete(Item.class, key);
    }
    public void insert(ReferanceBean referanceBean) {
        Referance beanEN = new Referance();
        Referance beanCN = new Referance();

        beanEN.setName(referanceBean.getName());
        beanEN.setCode(referanceBean.getCode());
        beanEN.setCategory(referanceBean.getCategory());
        beanEN.setLang(Constants.LANG_EN);
        beanEN.setText(referanceBean.getTextEN());

        beanCN.setName(referanceBean.getName());
        beanCN.setCode(referanceBean.getCode());
        beanCN.setCategory(referanceBean.getCategory());
        beanCN.setLang(Constants.LANG_ZH);
        beanCN.setText(referanceBean.getTextCN());

        super.insert(beanEN);
        super.insert(beanCN);

        String key = String.format(FORMAT_3, beanEN.getName(), beanEN.getLang(), beanEN.getCategory());
        cacheService.delete(Item.class, key);

        key = String.format(FORMAT_3, beanCN.getName(), beanCN.getLang(), beanCN.getCategory());
        cacheService.delete(Item.class, key);
    }

    public void update(Referance referance, boolean forced) {
        super.update(referance, forced);

        cacheService.delete(Referance.class, referance.getId());
        String key = String.format(FORMAT_3, referance.getName(), referance.getLang(), referance.getCategory());
        cacheService.delete(Item.class, key);
        key = String.format(FORMAT_4, referance.getName(), referance.getCode(), referance.getLang(),
                referance.getCategory());
        cacheService.delete(Item.class, key);
    }
    public void delete(Long id) {
        Referance referance = super.get(id);
        super.delete(id);

        cacheService.delete(Referance.class, id);
        String key = String.format(FORMAT_3, referance.getName(), referance.getLang(), referance.getCategory());
        cacheService.delete(Item.class, key);
        key = String.format(FORMAT_4, referance.getName(), referance.getCode(), referance.getLang(),
                referance.getCategory());
        cacheService.delete(Item.class, key);
    }

    public String getText(String name, String code, String lang) {
        return getText(name, code, lang, DEFAULT_CATEGORY);
    }
    public String getText(String name, String code, String lang, String category) {
        if (code == null || code.length() <= 0) {
            return DEFAULT_TEXT;
        }

        String key = String.format(FORMAT_4, name, code, lang, category);
        Item item = (Item) cacheService.get(Item.class, key);

        if (item == null) {
            ReferanceSearch search = new ReferanceSearch();
            search.setName(name);
            search.setCode(code);
            search.setLang(lang);
            search.setCategory(category);
            item = referanceMapper.selectItemByExample(search);
            if (item != null) {
                cacheService.add(Item.class, key, item);
            }
        }

        if (item != null) {
            return item.getValue();
        }
        return DEFAULT_TEXT;
    }
    public List<Item> findItem(String name, String lang) {
        return findItem(name, lang, DEFAULT_CATEGORY);
    }

    public List<Item> findItem(String name, String lang, String category) {
        String key = String.format(FORMAT_3, name, lang, category);
        @SuppressWarnings("unchecked")
        List<Item> list = (List<Item>) cacheService.get(Item.class, key);

        if (list == null) {
            ReferanceSearch search = new ReferanceSearch();
            search.setName(name);
            search.setLang(lang);
            search.setCategory(category);
            list = referanceMapper.selectListByExample(search);
            list.add(0, Item.DEFUALT_ITEM);
            cacheService.add(Item.class, key, list);
        }

        return list;
    }

    public void setReferanceMapper(ReferanceMapper referanceMapper) {
        this.referanceMapper = referanceMapper;
    }

    public void setCacheService(CacheService cacheService) {
        this.cacheService = cacheService;
    }
}
