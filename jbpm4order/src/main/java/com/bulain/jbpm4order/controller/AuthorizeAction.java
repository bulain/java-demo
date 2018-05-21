package com.bulain.jbpm4order.controller;

import java.util.List;

import com.bulain.common.controller.PageSupportActionSupport;
import com.bulain.common.model.Authorize;
import com.bulain.common.pojo.AuthorizeSearch;
import com.bulain.common.service.AuthorizeService;

public class AuthorizeAction extends PageSupportActionSupport {
    private static final long serialVersionUID = 4764266080241908757L;

    private Long id;

    private AuthorizeSearch search;
    private Authorize authorize;
    private List<Authorize> listAuthorize;

    private transient AuthorizeService authorizeService;

    public String list() {
        search = (AuthorizeSearch) getSearchFromSession(AuthorizeSearch.class, search);
        page = getPageFromSession();

        listAuthorize = authorizeService.page(search, page);

        putSearchToSession(AuthorizeSearch.class, search);
        putPageToSession();

        return SUCCESS;
    }

    public String newn() {
        authorize = new Authorize();
        return SUCCESS;
    }
    public String create() {
        authorizeService.insert(authorize);
        return SUCCESS;
    }
    public String show() {
        authorize = authorizeService.get(id);
        return SUCCESS;
    }
    public String edit() {
        authorize = authorizeService.get(id);
        return SUCCESS;
    }
    public String update() {
        authorizeService.update(authorize, false);
        return SUCCESS;
    }
    public String destroy() {
        authorizeService.delete(id);
        return SUCCESS;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AuthorizeSearch getSearch() {
        return search;
    }

    public void setSearch(AuthorizeSearch search) {
        this.search = search;
    }

    public Authorize getAuthorize() {
        return authorize;
    }

    public void setAuthorize(Authorize authorize) {
        this.authorize = authorize;
    }

    public List<Authorize> getListAuthorize() {
        return listAuthorize;
    }

    public void setListAuthorize(List<Authorize> listAuthorize) {
        this.listAuthorize = listAuthorize;
    }

    public void setAuthorizeService(AuthorizeService authorizeService) {
        this.authorizeService = authorizeService;
    }
}
