package com.bulain.activiti.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bulain.activiti.model.Referance;
import com.bulain.activiti.pojo.Item;
import com.bulain.activiti.pojo.ItemConst;
import com.bulain.activiti.pojo.ReferanceBean;
import com.bulain.activiti.pojo.ReferanceSearch;
import com.bulain.activiti.pojo.ReferanceView;
import com.bulain.activiti.service.ReferanceService;
import com.bulain.common.controller.PageSupportActionSupport;

public class ReferanceAction extends PageSupportActionSupport {
    private static final long serialVersionUID = -6586209271699101686L;
    private static final Logger LOG = LoggerFactory.getLogger(ReferanceAction.class);

    private static final String TEXT_REFERANCE_MODEL = "referance.model";
    private static final String TEXT_REFERANCE_NAME = "referance.name";
    private static final String TEXT_REFERANCE_CODE = "referance.code";
    private static final String TEXT_REFERANCE_LANG = "referance.lang";
    private static final String TEXT_REFERANCE_CATEGORY = "referance.category";

    private Long id;

    private ReferanceSearch search;
    private Referance referance;
    private ReferanceBean referanceBean;
    private List<ReferanceView> listReferance;

    private ReferanceService referanceService;

    private List<Item> listReferanceName;
    private List<Item> listReferanceLang;
    private List<Item> listReferanceCategory;

    public String list() {
        search = (ReferanceSearch) getSearchFromSession(ReferanceSearch.class, search);
        page = getPageFromSession();

        List<Referance> list = referanceService.page(search, page);
        listReferance = formatList(list);

        putSearchToSession(ReferanceSearch.class, search);
        putPageToSession();

        return SUCCESS;
    }

    public String newn() {
        referanceBean = new ReferanceBean();
        return SUCCESS;
    }
    public String create() {
        try {
            referanceService.insert(referanceBean);
            String msg = getText("info.create", new String[]{getText(TEXT_REFERANCE_MODEL)});
            addActionMessage(msg);
        } catch (Exception e) {
            LOG.error("create()", e);
            String msg = getText("error.create", new String[]{getText(TEXT_REFERANCE_MODEL)});
            addActionError(msg);
            return ERROR;
        }
        return SUCCESS;
    }
    public String show() {
        referance = referanceService.get(id);
        referance = formatItem(referance);
        return SUCCESS;
    }
    public String edit() {
        referance = referanceService.get(id);
        return SUCCESS;
    }
    public String update() {
        try {
            referanceService.update(referance, false);
            String msg = getText("info.update", new String[]{getText(TEXT_REFERANCE_MODEL)});
            addActionMessage(msg);
        } catch (Exception e) {
            LOG.error("update()", e);
            String msg = getText("error.update", new String[]{getText(TEXT_REFERANCE_MODEL)});
            addActionError(msg);
            return ERROR;
        }
        return SUCCESS;
    }
    public String destroy() {
        referanceService.delete(id);
        return SUCCESS;
    }

    public void validate() {
        super.validate();
        if (this.hasErrors()) {
            prepareEdit();
        }
    }
    public void prepareList() {
        listReferanceName = referanceService.findItem(ItemConst.NAME_REFERANCE, getLanguage());
        listReferanceLang = referanceService.findItem(ItemConst.NAME_LANGUAGE, getLanguage());
        listReferanceCategory = referanceService.findItem(ItemConst.NAME_CATEGORY, getLanguage());
    }
    public void prepareNewn() {
        prepareList();
    }
    public void prepareEdit() {
        prepareList();
    }
    public void prepareCreate() {
        prepareList();
    }
    public void prepareUpdate() {
        prepareList();
        if (id != null) {
            referance = referanceService.get(id);
        }
    }

    public void validateCreate() {
        Referance ref = new Referance();
        ref.setName(referanceBean.getName());
        ref.setCode(referanceBean.getCode());
        ref.setCategory(referanceBean.getCategory());
        long cnt = referanceService.countByDuplicate(ref);
        if (cnt > 0) {
            String msg = getText("validate.duplicate.3", new String[]{getText(TEXT_REFERANCE_NAME),
                    getText(TEXT_REFERANCE_CODE), getText(TEXT_REFERANCE_CATEGORY)});
            addActionError(msg);
        }
    }

    public void validateUpdate() {
        long cnt = referanceService.countByDuplicate(referance);
        if (cnt > 0) {
            String msg = getText("validate.duplicate.4", new String[]{getText(TEXT_REFERANCE_NAME),
                    getText(TEXT_REFERANCE_CODE), getText(TEXT_REFERANCE_LANG), getText(TEXT_REFERANCE_CATEGORY)});
            addActionError(msg);
        }
    }

    protected List<ReferanceView> formatList(List<Referance> list) {
        List<ReferanceView> listView = new ArrayList<ReferanceView>();
        for (Referance ref : list) {
            listView.add(formatItem(ref));
        }
        return listView;
    }

    protected ReferanceView formatItem(Referance ref) {
        ReferanceView refv = new ReferanceView(ref);
        refv.setNameName(referanceService.getText(ItemConst.NAME_REFERANCE, refv.getName(), getLanguage()));
        refv.setLangName(referanceService.getText(ItemConst.NAME_LANGUAGE, refv.getLang(), getLanguage()));
        refv.setCategoryName(referanceService.getText(ItemConst.NAME_CATEGORY, refv.getCategory(), getLanguage()));
        return refv;
    }

    public ReferanceSearch getSearch() {
        return search;
    }

    public void setSearch(ReferanceSearch search) {
        this.search = search;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Referance getReferance() {
        return referance;
    }

    public void setReferance(Referance referance) {
        this.referance = referance;
    }

    public List<ReferanceView> getListReferance() {
        return listReferance;
    }

    public void setListReferance(List<ReferanceView> listReferance) {
        this.listReferance = listReferance;
    }

    public void setReferanceService(ReferanceService referanceService) {
        this.referanceService = referanceService;
    }

    public ReferanceBean getReferanceBean() {
        return referanceBean;
    }

    public void setReferanceBean(ReferanceBean referanceBean) {
        this.referanceBean = referanceBean;
    }

    public List<Item> getListReferanceName() {
        return listReferanceName;
    }

    public void setListReferanceName(List<Item> listReferanceName) {
        this.listReferanceName = listReferanceName;
    }

    public List<Item> getListReferanceLang() {
        return listReferanceLang;
    }

    public void setListReferanceLang(List<Item> listReferanceLang) {
        this.listReferanceLang = listReferanceLang;
    }

    public List<Item> getListReferanceCategory() {
        return listReferanceCategory;
    }

    public void setListReferanceCategory(List<Item> listReferanceCategory) {
        this.listReferanceCategory = listReferanceCategory;
    }

}
