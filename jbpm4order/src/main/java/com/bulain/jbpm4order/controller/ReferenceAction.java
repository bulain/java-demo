package com.bulain.jbpm4order.controller;

import java.util.ArrayList;
import java.util.List;

import com.bulain.common.controller.PageSupportActionSupport;
import com.bulain.common.model.Reference;
import com.bulain.common.model.ReferenceBean;
import com.bulain.common.pojo.Item;
import com.bulain.common.pojo.ItemConst;
import com.bulain.common.pojo.ReferenceSearch;
import com.bulain.common.pojo.ReferenceView;
import com.bulain.common.service.ReferenceService;

public class ReferenceAction extends PageSupportActionSupport {
    private static final long serialVersionUID = -6586209271699101686L;

    private Long id;

    private ReferenceSearch search;
    private Reference reference;
    private ReferenceBean referenceBean;
    private List<ReferenceView> listReference;

    private transient ReferenceService referenceService;

    private List<Item> listReferenceName;
    private List<Item> listReferenceLang;
    private List<Item> listReferenceCatagory;

    public String list() {
        search = (ReferenceSearch) getSearchFromSession(ReferenceSearch.class, search);
        page = getPageFromSession();

        List<Reference> list = referenceService.page(search, page);
        listReference = formatList(list);

        putSearchToSession(ReferenceSearch.class, search);
        putPageToSession();

        return SUCCESS;
    }

    public String newn() {
        referenceBean = new ReferenceBean();
        prepareEdit();
        return SUCCESS;
    }
    public String create() {
        referenceService.insert(referenceBean);
        return SUCCESS;
    }
    public String show() {
        reference = referenceService.get(id);
        reference = formatItem(reference);
        return SUCCESS;
    }
    public String edit() {
        reference = referenceService.get(id);
        prepareEdit();
        return SUCCESS;
    }
    public String update() {
        referenceService.update(reference, false);
        return SUCCESS;
    }
    public String destroy() {
        referenceService.delete(id);
        return SUCCESS;
    }

    public void validate() {
        super.validate();
        if (this.hasErrors()) {
            prepareEdit();
        }
    }
    public void prepareList() {
        listReferenceName = referenceService.findItem(ItemConst.NAME_REFERANCE, getLanguage());
        listReferenceLang = referenceService.findItem(ItemConst.NAME_LANGUAGE, getLanguage());
        listReferenceCatagory = referenceService.findItem(ItemConst.NAME_CATAGORY, getLanguage());
    }
    public void prepareNewn() {
        prepareList();
    }
    public void prepareEdit() {
        prepareList();
    }

    protected List<ReferenceView> formatList(List<Reference> list) {
        List<ReferenceView> listView = new ArrayList<ReferenceView>();
        for (Reference ref : list) {
            listView.add(formatItem(ref));
        }
        return listView;
    }

    protected ReferenceView formatItem(Reference ref) {
        ReferenceView refv = new ReferenceView(ref);
        refv.setNameName(referenceService.getText(ItemConst.NAME_REFERANCE, refv.getName(), getLanguage()));
        refv.setLangName(referenceService.getText(ItemConst.NAME_LANGUAGE, refv.getLang(), getLanguage()));
        refv.setCatagoryName(referenceService.getText(ItemConst.NAME_CATAGORY, refv.getCatagory(), getLanguage()));
        return refv;
    }

    public ReferenceSearch getSearch() {
        return search;
    }

    public void setSearch(ReferenceSearch search) {
        this.search = search;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Reference getReference() {
        return reference;
    }

    public void setReference(Reference reference) {
        this.reference = reference;
    }

    public List<ReferenceView> getListReference() {
        return listReference;
    }

    public void setListReference(List<ReferenceView> listReference) {
        this.listReference = listReference;
    }

    public void setReferenceService(ReferenceService referenceService) {
        this.referenceService = referenceService;
    }

    public ReferenceBean getReferenceBean() {
        return referenceBean;
    }

    public void setReferenceBean(ReferenceBean referenceBean) {
        this.referenceBean = referenceBean;
    }

    public List<Item> getListReferenceName() {
        return listReferenceName;
    }

    public void setListReferenceName(List<Item> listReferenceName) {
        this.listReferenceName = listReferenceName;
    }

    public List<Item> getListReferenceLang() {
        return listReferenceLang;
    }

    public void setListReferenceLang(List<Item> listReferenceLang) {
        this.listReferenceLang = listReferenceLang;
    }

    public List<Item> getListReferenceCatagory() {
        return listReferenceCatagory;
    }

    public void setListReferenceCatagory(List<Item> listReferenceCatagory) {
        this.listReferenceCatagory = listReferenceCatagory;
    }

}
