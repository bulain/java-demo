package com.bulain.common.service;

import java.util.List;

import com.bulain.common.cache.CacheService;
import com.bulain.common.cache.NoCacheServiceImpl;
import com.bulain.common.dao.PagedMapper;
import com.bulain.common.service.PagedServiceImpl;
import com.bulain.common.dao.ReferenceMapper;
import com.bulain.common.model.Constants;
import com.bulain.common.model.Reference;
import com.bulain.common.model.ReferenceBean;
import com.bulain.common.pojo.Item;
import com.bulain.common.pojo.ReferenceSearch;

public class ReferenceServiceImpl extends PagedServiceImpl<Reference, ReferenceSearch> implements ReferenceService {
    private static final String COMA = "__";

    private static final String DEFAULT_CATAGORY = "";
    private static final String DEFAULT_TEXT = "";

    private ReferenceMapper referenceMapper;
    private CacheService cacheService = new NoCacheServiceImpl();

    @Override
    protected PagedMapper<Reference, ReferenceSearch> getPagedMapper() {
        return referenceMapper;
    }

    public Reference get(Long id) {
        Object object = cacheService.get(Reference.class, id);
        if (object == null) {
            object = super.get(id);
            cacheService.add(Reference.class, id, object);
        }
        return (Reference) object;
    }
    public void insert(Reference reference) {
        super.insert(reference);

        String key = reference.getName() + COMA + reference.getLang() + COMA + reference.getCatagory();
        cacheService.delete(Item.class, key);
    }
    public void insert(ReferenceBean referenceBean) {
        Reference beanEN = new Reference();
        Reference beanCN = new Reference();

        beanEN.setName(referenceBean.getName());
        beanEN.setCode(referenceBean.getCode());
        beanEN.setCatagory(referenceBean.getCatagory());
        beanEN.setLang(Constants.LANG_EN);
        beanEN.setText(referenceBean.getTextEN());

        beanCN.setName(referenceBean.getName());
        beanCN.setCode(referenceBean.getCode());
        beanCN.setCatagory(referenceBean.getCatagory());
        beanCN.setLang(Constants.LANG_ZH);
        beanCN.setText(referenceBean.getTextCN());

        super.insert(beanEN);
        super.insert(beanCN);

        String key = beanEN.getName() + COMA + beanEN.getLang() + COMA + beanEN.getCatagory();
        cacheService.delete(Item.class, key);

        key = beanCN.getName() + COMA + beanCN.getLang() + COMA + beanCN.getCatagory();
        cacheService.delete(Item.class, key);
    }

    public void update(Reference reference, boolean forced) {
        super.update(reference, forced);

        cacheService.delete(Reference.class, reference.getId());
        String key = reference.getName() + COMA + reference.getLang() + COMA + reference.getCatagory();
        cacheService.delete(Item.class, key);
        key = reference.getName() + COMA + reference.getCode() + COMA + reference.getLang() + COMA
                + reference.getCatagory();
        cacheService.delete(Item.class, key);
    }
    public void delete(Long id) {
        Reference reference = super.get(id);
        super.delete(id);

        cacheService.delete(Reference.class, id);
        String key = reference.getName() + COMA + reference.getLang() + COMA + reference.getCatagory();
        cacheService.delete(Item.class, key);
        key = reference.getName() + COMA + reference.getCode() + COMA + reference.getLang() + COMA
                + reference.getCatagory();
        cacheService.delete(Item.class, key);
    }

    public String getText(String name, String code, String lang) {
        return getText(name, code, lang, DEFAULT_CATAGORY);
    }
    public String getText(String name, String code, String lang, String catagory) {
        if (code == null || code.length() <= 0) {
            return DEFAULT_TEXT;
        }

        String key = name + COMA + code + COMA + lang + COMA + catagory;
        Item item = (Item) cacheService.get(Item.class, key);

        if (item == null) {
            ReferenceSearch search = new ReferenceSearch();
            search.setName(name);
            search.setCode(code);
            search.setLang(lang);
            search.setCatagory(catagory);
            item = referenceMapper.selectItemByExample(search);
            if (item != null) {
                cacheService.add(Item.class, key, item);
            }
        }

        if (item != null) {
            return item.getValue();
        }
        return DEFAULT_TEXT;
    }

    public Item getItem(String name, String code, String lang) {
        String text = getText(name, code, lang);
        return new Item(code, text);
    }
    
    public Item getItem(String name, String code, String lang, String catagory) {
        String text = getText(name, code, lang, catagory);
        return new Item(code, text);
    }

    public List<Item> findItem(String name, String lang) {
        return findItem(name, lang, DEFAULT_CATAGORY);
    }

    public List<Item> findItem(String name, String lang, String catagory) {
        String key = name + COMA + lang + COMA + catagory;
        @SuppressWarnings("unchecked")
        List<Item> list = (List<Item>) cacheService.get(Item.class, key);

        if (list == null) {
            ReferenceSearch search = new ReferenceSearch();
            search.setName(name);
            search.setLang(lang);
            search.setCatagory(catagory);
            list = referenceMapper.selectListByExample(search);
            list.add(0, Item.DEFUALT_ITEM);
            cacheService.add(Item.class, key, list);
        }

        return list;
    }

    public void setReferenceMapper(ReferenceMapper referenceMapper) {
        this.referenceMapper = referenceMapper;
    }

    public void setCacheService(CacheService cacheService) {
        this.cacheService = cacheService;
    }
}
