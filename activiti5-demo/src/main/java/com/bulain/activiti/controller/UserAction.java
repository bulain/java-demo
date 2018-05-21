package com.bulain.activiti.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import com.bulain.activiti.model.User;
import com.bulain.activiti.pojo.UserSearch;
import com.bulain.activiti.pojo.UserView;
import com.bulain.activiti.service.UserService;
import com.bulain.common.controller.PageSupportActionSupport;

public class UserAction extends PageSupportActionSupport {
    private static final String TEXT_USER_MODEL = "user.model";
    private static final long serialVersionUID = -4301484346812182688L;
    private static final Logger LOG = LoggerFactory.getLogger(UserAction.class);

    private Long id;
    private UserSearch search;
    private User user;
    private List<UserView> listLogin;

    private transient UserService userService;

    public String list() {
        search = (UserSearch) getSearchFromSession(UserSearch.class, search);
        page = getPageFromSession();

        List<User> list = userService.page(search, page);
        listLogin = formatList(list);

        putSearchToSession(UserSearch.class, search);
        putPageToSession();

        return SUCCESS;
    }

    public String newn() {
        user = new User();
        return SUCCESS;
    }
    public String create() {
        try {
            userService.insert(user);
            String msg = getText("info.create", new String[]{getText(TEXT_USER_MODEL)});
            addActionMessage(msg);
        } catch (Exception e) {
            LOG.error("create()", e);
            String msg = getText("error.create", new String[]{getText(TEXT_USER_MODEL)});
            addActionError(msg);
            return ERROR;
        }
        return SUCCESS;
    }
    public String show() {
        user = userService.get(id);
        return SUCCESS;
    }
    public String edit() {
        user = userService.get(id);
        return SUCCESS;
    }
    public String update() {
        try {
            userService.update(user, true);
            String msg = getText("common.updateInfo", new String[]{TEXT_USER_MODEL});
            addActionMessage(msg);
        } catch (Exception e) {
            LOG.error("update()", e);
            String msg = getText("common.updateError", new String[]{TEXT_USER_MODEL});
            addActionError(msg);
            return ERROR;
        }
        return SUCCESS;
    }
    public String destroy() {
        try {
            userService.delete(id);
            String msg = getText("common.deleteInfo", new String[]{TEXT_USER_MODEL});
            addActionMessage(msg);
        } catch (Exception e) {
            LOG.error("destroy()", e);
            String msg = getText("common.deleteError", new String[]{TEXT_USER_MODEL});
            addActionError(msg);
            return ERROR;
        }

        return SUCCESS;
    }

    public void prepareList() {
    }

    public void prepareUpdate() {
        if (id != null) {
            user = userService.get(id);
        }
    }

    protected List<UserView> formatList(List<User> list) {
        List<UserView> listView = new ArrayList<UserView>();
        for (User lgn : list) {
            listView.add(formatItem(lgn));
        }
        return listView;
    }

    protected UserView formatItem(User user) {
        UserView view = new UserView();
        BeanUtils.copyProperties(user, view);
        return view;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public UserSearch getSearch() {
        return search;
    }

    public void setSearch(UserSearch search) {
        this.search = search;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<UserView> getListLogin() {
        return listLogin;
    }

    public void setListLogin(List<UserView> listLogin) {
        this.listLogin = listLogin;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
