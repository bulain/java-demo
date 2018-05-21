package com.bulain.jbpm4order.controller;

import java.util.ArrayList;
import java.util.List;

import com.bulain.common.controller.PageSupportActionSupport;
import com.bulain.common.model.Group;
import com.bulain.common.model.Login;
import com.bulain.common.model.Permission;
import com.bulain.common.pojo.GroupSearch;
import com.bulain.common.pojo.GroupView;
import com.bulain.common.service.GroupService;
import com.bulain.common.service.LoginService;

public class GroupAction extends PageSupportActionSupport {
    private static final long serialVersionUID = -4301484346812182688L;

    private Long id;

    private GroupSearch search;
    private Group group;
    private List<GroupView> listGroup;

    private List<Long> listLoginId;
    private List<Login> listLoginSrc;
    private List<Login> listLoginDist;

    private List<String> listPermission;
    private List<Permission> listPermissionSrc;
    private List<Permission> listPermissionDist;

    private transient GroupService groupService;
    private transient LoginService loginService;

    public String list() {
        search = (GroupSearch) getSearchFromSession(GroupSearch.class, search);
        page = getPageFromSession();

        List<Group> list = groupService.page(search, page);
        listGroup = formatList(list);

        putSearchToSession(GroupSearch.class, search);
        putPageToSession();

        return SUCCESS;
    }

    public String newn() {
        group = new Group();
        return SUCCESS;
    }
    public String create() {
        groupService.insert(group);
        return SUCCESS;
    }
    public String show() {
        group = groupService.get(id);
        return SUCCESS;
    }
    public String edit() {
        group = groupService.get(id);
        return SUCCESS;
    }
    public String update() {
        groupService.update(group, false);
        return SUCCESS;
    }
    public String destroy() {
        groupService.delete(id);
        return SUCCESS;
    }

    public String editLogin() {
        group = groupService.get(id);
        listLoginDist = loginService.findLoginByGroupId(id);
        listLoginSrc = loginService.findLoginByNoInGroupId(id);
        return SUCCESS;
    }
    public String updateLogin() {
        groupService.updateGroupLogin(id, listLoginId);
        return SUCCESS;
    }
    public String editPermission() {
        group = groupService.get(id);
        listPermissionDist = groupService.findPermissionByGroupId(id);
        listPermissionSrc = groupService.findPermissionByNoGroupId(id);
        return SUCCESS;
    }
    public String updatePermission() {
        groupService.updateGroupPermission(id, listPermission);
        return SUCCESS;
    }

    // //ajax
    // public String ajaxCreateGroupLogin(){
    // groupService.insertGroupLogin(groupLogin);
    // return SUCCESS;
    // }
    // public String ajaxDeleteGroupLogin(){
    // groupService.deleteGroupLogin(id);
    // return SUCCESS;
    // }
    // public String ajaxCreateGroupPermission(){
    // groupService.insertGroupPermission(groupPermission);
    // return SUCCESS;
    // }
    // public String ajaxDeleteGroupPermission(){
    // groupService.deleteGroupPermission(id);
    // return SUCCESS;
    // }

    protected List<GroupView> formatList(List<Group> list) {
        List<GroupView> listView = new ArrayList<GroupView>();
        for (Group grp : list) {
            listView.add(formatItem(grp));
        }
        return listView;
    }

    protected GroupView formatItem(Group grp) {
        GroupView grpView = new GroupView(grp);
        grpView.setCountGroupLogin(loginService.countLoginByGroupId(grpView.getId()));
        grpView.setListPermission(groupService.findPermissionByGroupId(grpView.getId()));
        return grpView;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public GroupSearch getSearch() {
        return search;
    }
    public void setSearch(GroupSearch search) {
        this.search = search;
    }
    public Group getGroup() {
        return group;
    }
    public void setGroup(Group group) {
        this.group = group;
    }
    public List<GroupView> getListGroup() {
        return listGroup;
    }
    public void setListGroup(List<GroupView> listGroup) {
        this.listGroup = listGroup;
    }
    public void setGroupService(GroupService groupService) {
        this.groupService = groupService;
    }
    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }
    public List<Login> getListLoginSrc() {
        return listLoginSrc;
    }
    public void setListLoginSrc(List<Login> listLoginSrc) {
        this.listLoginSrc = listLoginSrc;
    }
    public List<Login> getListLoginDist() {
        return listLoginDist;
    }
    public void setListLoginDist(List<Login> listLoginDist) {
        this.listLoginDist = listLoginDist;
    }
    public List<Permission> getListPermissionSrc() {
        return listPermissionSrc;
    }
    public void setListPermissionSrc(List<Permission> listPermissionSrc) {
        this.listPermissionSrc = listPermissionSrc;
    }
    public List<Permission> getListPermissionDist() {
        return listPermissionDist;
    }
    public void setListPermissionDist(List<Permission> listPermissionDist) {
        this.listPermissionDist = listPermissionDist;
    }
    public List<Long> getListLoginId() {
        return listLoginId;
    }
    public void setListLoginId(List<Long> listLoginId) {
        this.listLoginId = listLoginId;
    }
    public List<String> getListPermission() {
        return listPermission;
    }
    public void setListPermission(List<String> listPermission) {
        this.listPermission = listPermission;
    }
}
