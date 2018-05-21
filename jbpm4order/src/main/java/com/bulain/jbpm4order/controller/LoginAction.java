package com.bulain.jbpm4order.controller;

import java.util.ArrayList;
import java.util.List;

import com.bulain.common.controller.PageSupportActionSupport;
import com.bulain.common.model.Group;
import com.bulain.common.model.Login;
import com.bulain.common.pojo.Item;
import com.bulain.common.pojo.ItemConst;
import com.bulain.common.pojo.LoginSearch;
import com.bulain.common.pojo.LoginView;
import com.bulain.common.pojo.Master;
import com.bulain.common.service.GroupService;
import com.bulain.common.service.LoginService;
import com.bulain.common.service.MasterService;
import com.bulain.common.service.ReferenceService;

public class LoginAction extends PageSupportActionSupport {
    private static final long serialVersionUID = -4301484346812182688L;

    private Long id;

    private LoginSearch search;
    private Login login;
    private List<LoginView> listLogin;

    private List<Long> listGroupId;
    private List<Group> listGroupSrc;
    private List<Group> listGroupDist;

    private transient LoginService loginService;
    private transient GroupService groupService;
    private transient MasterService masterService;
    private transient ReferenceService referenceService;

    private List<Master> listMasterGroup;
    private List<Master> listMasterPerson;
    private List<Item> listReferenceBoolean;

    public String list() {
        search = (LoginSearch) getSearchFromSession(LoginSearch.class, search);
        page = getPageFromSession();

        List<Login> list = loginService.page(search, page);
        listLogin = formatList(list);

        putSearchToSession(LoginSearch.class, search);
        putPageToSession();

        return SUCCESS;
    }

    public String newn() {
        login = new Login();
        prepareEdit();
        return SUCCESS;
    }
    public String create() {
        loginService.insert(login);
        return SUCCESS;
    }
    public String show() {
        login = loginService.get(id);
        return SUCCESS;
    }
    public String edit() {
        login = loginService.get(id);
        prepareEdit();
        return SUCCESS;
    }
    public String update() {
        loginService.update(login, false);
        return SUCCESS;
    }
    public String destroy() {
        loginService.delete(id);
        return SUCCESS;
    }
    public String editGroup() {
        login = loginService.get(id);
        listGroupDist = groupService.findGroupByLoginId(id);
        listGroupSrc = groupService.findGroupByNoLoginId(id);
        return SUCCESS;
    }
    public String updateGroup() {
        loginService.updateGroupLogin(id, listGroupId);
        return SUCCESS;
    }

    public void validate() {
        super.validate();
        if (this.hasErrors()) {
            prepareEdit();
        }
    }
    public void prepareList() {
        listReferenceBoolean = referenceService.findItem(ItemConst.NAME_BOOLEAN, getLanguage());
        listMasterGroup = masterService.findMaster4Group();
        listMasterPerson = masterService.findMaster4Person();
    }
    public void prepareNewn() {
        prepareList();
    }
    public void prepareEdit() {
        prepareList();
    }

    protected List<LoginView> formatList(List<Login> list) {
        List<LoginView> listView = new ArrayList<LoginView>();
        for (Login lgn : list) {
            listView.add(formatItem(lgn));
        }
        return listView;
    }

    protected LoginView formatItem(Login lgn) {
        LoginView lgnView = new LoginView(lgn);
        lgnView.setEnabledName(referenceService.getText(ItemConst.NAME_BOOLEAN, lgnView.getEnabled(), getLanguage()));
        lgnView.setListGroup(groupService.findGroupByLoginId(lgnView.getId()));
        lgnView.setPersonName(masterService.getValue4Person(lgnView.getPersonId()));

        return lgnView;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public LoginSearch getSearch() {
        return search;
    }
    public void setSearch(LoginSearch search) {
        this.search = search;
    }
    public Login getLogin() {
        return login;
    }
    public void setLogin(Login login) {
        this.login = login;
    }
    public List<LoginView> getListLogin() {
        return listLogin;
    }
    public void setListLogin(List<LoginView> listLogin) {
        this.listLogin = listLogin;
    }
    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }
    public List<Group> getListGroupSrc() {
        return listGroupSrc;
    }
    public void setListGroupSrc(List<Group> listGroupSrc) {
        this.listGroupSrc = listGroupSrc;
    }
    public List<Group> getListGroupDist() {
        return listGroupDist;
    }
    public void setListGroupDist(List<Group> listGroupDist) {
        this.listGroupDist = listGroupDist;
    }
    public List<Master> getListMasterGroup() {
        return listMasterGroup;
    }
    public void setListMasterGroup(List<Master> listMasterGroup) {
        this.listMasterGroup = listMasterGroup;
    }
    public void setGroupService(GroupService groupService) {
        this.groupService = groupService;
    }
    public List<Item> getListReferenceBoolean() {
        return listReferenceBoolean;
    }
    public void setListReferenceBoolean(List<Item> listReferenceBoolean) {
        this.listReferenceBoolean = listReferenceBoolean;
    }
    public void setReferenceService(ReferenceService referenceService) {
        this.referenceService = referenceService;
    }
    public List<Long> getListGroupId() {
        return listGroupId;
    }
    public void setListGroupId(List<Long> listGroupId) {
        this.listGroupId = listGroupId;
    }
    public void setMasterService(MasterService masterService) {
        this.masterService = masterService;
    }
    public List<Master> getListMasterPerson() {
        return listMasterPerson;
    }
    public void setListMasterPerson(List<Master> listMasterPerson) {
        this.listMasterPerson = listMasterPerson;
    }
}
