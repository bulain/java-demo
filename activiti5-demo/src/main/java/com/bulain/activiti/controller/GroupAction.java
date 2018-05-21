package com.bulain.activiti.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import com.bulain.activiti.model.Group;
import com.bulain.activiti.pojo.GroupSearch;
import com.bulain.activiti.pojo.GroupView;
import com.bulain.activiti.pojo.Item;
import com.bulain.activiti.pojo.ItemConst;
import com.bulain.activiti.service.GroupService;
import com.bulain.activiti.service.ReferanceService;
import com.bulain.common.controller.PageSupportActionSupport;

public class GroupAction extends PageSupportActionSupport {
    private static final String TEXT_GROUP_TYPE = "group.type";
    private static final String TEXT_GROUP_NAME = "group.name";
    private static final String TEXT_GROUP_MODEL = "group.model";
    private static final long serialVersionUID = -4580561000078777884L;
    private static final Logger LOG = LoggerFactory.getLogger(GroupAction.class);

    private Long id;
    private GroupSearch search;
    private Group group;
    private List<GroupView> listGroup;

    private transient GroupService groupService;
    private transient ReferanceService referanceService;

    private List<Item> listReferanceGroupType;

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
        try {
            groupService.insert(group);
            String msg = getText("info.create", new String[]{getText(TEXT_GROUP_MODEL)});
            addActionMessage(msg);
        } catch (Exception e) {
            LOG.error("create()", e);
            String msg = getText("error.create", new String[]{getText(TEXT_GROUP_MODEL)});
            addActionError(msg);
            return ERROR;
        }
        return SUCCESS;
    }
    public String show() {
        group = groupService.get(id);
        group = formatItem(group);
        return SUCCESS;
    }
    public String edit() {
        group = groupService.get(id);
        return SUCCESS;
    }
    public String update() {
        try {
            groupService.update(group, true);
            String msg = getText("common.updateInfo", new String[]{getText(TEXT_GROUP_MODEL)});
            addActionMessage(msg);
        } catch (Exception e) {
            LOG.error("update()", e);
            String msg = getText("common.updateError", new String[]{getText(TEXT_GROUP_MODEL)});
            addActionError(msg);
            return ERROR;
        }
        return SUCCESS;
    }
    public String destroy() {
        try {
            groupService.delete(id);
            String msg = getText("common.deleteInfo", new String[]{getText(TEXT_GROUP_MODEL)});
            addActionMessage(msg);
        } catch (Exception e) {
            LOG.error("destroy()", e);
            String msg = getText("common.deleteError", new String[]{getText(TEXT_GROUP_MODEL)});
            addActionError(msg);
            return ERROR;
        }

        return SUCCESS;
    }

    public void prepareList() {
        listReferanceGroupType = referanceService.findItem(ItemConst.NAME_GROUP_TYPE, getLanguage());
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
        if (id != null) {
            group = groupService.get(id);
        }
        prepareList();
    }

    public void validateCreate() {
        long cnt = groupService.countByDuplicate(group);
        if (cnt > 0) {
            String msg = getText("validate.duplicate.2", new String[]{getText(TEXT_GROUP_NAME),
                    getText(TEXT_GROUP_TYPE)});
            addActionError(msg);
        }
    }

    public void validateUpdate() {
        validateCreate();
    }

    protected List<GroupView> formatList(List<Group> list) {
        List<GroupView> listView = new ArrayList<GroupView>();
        for (Group lgn : list) {
            listView.add(formatItem(lgn));
        }
        return listView;
    }

    protected GroupView formatItem(Group group) {
        GroupView view = new GroupView();
        BeanUtils.copyProperties(group, view);

        view.setTypeName(referanceService.getText(ItemConst.NAME_GROUP_TYPE, group.getType(), getLanguage()));

        return view;
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

    public List<Item> getListReferanceGroupType() {
        return listReferanceGroupType;
    }

    public void setListReferanceGroupType(List<Item> listReferanceGroupType) {
        this.listReferanceGroupType = listReferanceGroupType;
    }

    public void setReferanceService(ReferanceService referanceService) {
        this.referanceService = referanceService;
    }

}
