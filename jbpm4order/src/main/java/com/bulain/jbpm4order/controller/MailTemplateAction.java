package com.bulain.jbpm4order.controller;

import java.util.ArrayList;
import java.util.List;

import com.bulain.common.controller.PageSupportActionSupport;
import com.bulain.common.model.MailTemplate;
import com.bulain.common.pojo.Item;
import com.bulain.common.pojo.ItemConst;
import com.bulain.common.pojo.MailTemplateSearch;
import com.bulain.common.pojo.MailTemplateView;
import com.bulain.common.service.MailTemplateService;
import com.bulain.common.service.ReferenceService;

public class MailTemplateAction extends PageSupportActionSupport {
    private static final long serialVersionUID = -4573803081036830823L;

    private Long id;

    private MailTemplateSearch search;
    private MailTemplate mailTemplate;
    private List<MailTemplateView> listMailTemplate;

    private transient MailTemplateService mailTemplateService;
    private transient ReferenceService referenceService;

    private List<Item> listReferenceLang;

    public String list() {
        search = (MailTemplateSearch) getSearchFromSession(MailTemplateSearch.class, search);
        page = getPageFromSession();

        List<MailTemplate> list = mailTemplateService.page(search, page);
        listMailTemplate = formatList(list);

        putSearchToSession(MailTemplateSearch.class, search);
        putPageToSession();

        return SUCCESS;
    }

    public String newn() {
        mailTemplate = new MailTemplateView();
        prepareEdit();
        return SUCCESS;
    }
    public String create() {
        if (mailTemplate != null && mailTemplate.getBodyName() != null) {
            mailTemplate.setBody(mailTemplate.getBodyName().getBytes());
        }
        mailTemplateService.insert(mailTemplate);
        return SUCCESS;
    }
    public String show() {
        mailTemplate = mailTemplateService.get(id);
        mailTemplate = formatItem(mailTemplate);
        return SUCCESS;
    }
    public String edit() {
        mailTemplate = mailTemplateService.get(id);
        mailTemplate = formatItem(mailTemplate);
        prepareEdit();
        return SUCCESS;
    }
    public String update() {
        if (mailTemplate != null && mailTemplate.getBodyName() != null) {
            mailTemplate.setBody(mailTemplate.getBodyName().getBytes());
        }
        mailTemplateService.update(mailTemplate, false);
        return SUCCESS;
    }
    public String destroy() {
        mailTemplateService.delete(id);
        return SUCCESS;
    }

    public void validate() {
        super.validate();
        if (this.hasErrors()) {
            prepareEdit();
        }
    }

    public void prepareList() {
        listReferenceLang = referenceService.findItem(ItemConst.NAME_LANGUAGE, getLanguage());
    }
    protected void prepareNewn() {
        prepareList();
    }
    protected void prepareEdit() {
        prepareList();
    }

    protected List<MailTemplateView> formatList(List<MailTemplate> list) {
        List<MailTemplateView> listView = new ArrayList<MailTemplateView>();
        for (MailTemplate ref : list) {
            listView.add(formatItem(ref));
        }
        return listView;
    }

    protected MailTemplateView formatItem(MailTemplate mailTemplate) {
        MailTemplateView refv = new MailTemplateView(mailTemplate);
        refv.setLangName(referenceService.getText(ItemConst.NAME_LANGUAGE, refv.getLang(), getLanguage()));
        return refv;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MailTemplateSearch getSearch() {
        return search;
    }

    public void setSearch(MailTemplateSearch search) {
        this.search = search;
    }

    public MailTemplate getMailTemplate() {
        return mailTemplate;
    }

    public void setMailTemplate(MailTemplate mailTemplate) {
        this.mailTemplate = mailTemplate;
    }

    public List<MailTemplateView> getListMailTemplate() {
        return listMailTemplate;
    }

    public void setListMailTemplate(List<MailTemplateView> listMailTemplate) {
        this.listMailTemplate = listMailTemplate;
    }

    public void setMailTemplateService(MailTemplateService mailTemplateService) {
        this.mailTemplateService = mailTemplateService;
    }

    public List<Item> getListReferenceLang() {
        return listReferenceLang;
    }

    public void setListReferenceLang(List<Item> listReferenceLang) {
        this.listReferenceLang = listReferenceLang;
    }

    public void setReferenceService(ReferenceService referenceService) {
        this.referenceService = referenceService;
    }

}
