package com.bulain.activiti.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import com.bulain.activiti.model.Category;
import com.bulain.activiti.pojo.CategorySearch;
import com.bulain.activiti.pojo.CategoryView;
import com.bulain.activiti.pojo.Item;
import com.bulain.activiti.pojo.ItemConst;
import com.bulain.activiti.service.CategoryService;
import com.bulain.activiti.service.ReferanceService;
import com.bulain.common.controller.PageSupportActionSupport;

public class CategoryAction extends PageSupportActionSupport {
    private static final String TEXT_CATEGORY_MODEL = "category.model";
    private static final String TEXT_CATEGORY_NAME = "category.name";
    private static final String TEXT_CATEGORY_TYPE = "category.type";
    private static final long serialVersionUID = -4580561000078777884L;
    private static final Logger LOG = LoggerFactory.getLogger(CategoryAction.class);

    private Long id;
    private CategorySearch search;
    private Category category;
    private List<CategoryView> listCategory;

    private transient CategoryService categoryService;
    private transient ReferanceService referanceService;

    private List<Item> listReferanceCategoryType;

    public String list() {
        search = (CategorySearch) getSearchFromSession(CategorySearch.class, search);
        page = getPageFromSession();

        List<Category> list = categoryService.page(search, page);
        listCategory = formatList(list);

        putSearchToSession(CategorySearch.class, search);
        putPageToSession();

        return SUCCESS;
    }

    public String newn() {
        category = new Category();
        return SUCCESS;
    }

    public String create() {
        try {
            categoryService.insert(category);
            String msg = getText("info.create", new String[]{getText(TEXT_CATEGORY_MODEL)});
            addActionMessage(msg);
        } catch (Exception e) {
            LOG.error("create()", e);
            String msg = getText("error.create", new String[]{getText(TEXT_CATEGORY_MODEL)});
            addActionError(msg);
            return ERROR;
        }
        return SUCCESS;
    }

    public String show() {
        category = categoryService.get(id);
        category = formatItem(category);
        return SUCCESS;
    }

    public String edit() {
        category = categoryService.get(id);
        return SUCCESS;
    }

    public String update() {
        try {
            categoryService.update(category, true);
            String msg = getText("common.updateInfo", new String[]{getText(TEXT_CATEGORY_MODEL)});
            addActionMessage(msg);
        } catch (Exception e) {
            LOG.error("update()", e);
            String msg = getText("common.updateError", new String[]{getText(TEXT_CATEGORY_MODEL)});
            addActionError(msg);
            return ERROR;
        }
        return SUCCESS;
    }
    public String destroy() {
        try {
            categoryService.delete(id);
            String msg = getText("common.deleteInfo", new String[]{getText(TEXT_CATEGORY_MODEL)});
            addActionMessage(msg);
        } catch (Exception e) {
            LOG.error("destroy()", e);
            String msg = getText("common.deleteError", new String[]{getText(TEXT_CATEGORY_MODEL)});
            addActionError(msg);
            return ERROR;
        }

        return SUCCESS;
    }

    public void prepareList() {
        listReferanceCategoryType = referanceService.findItem(ItemConst.NAME_CATEGORY_TYPE, getLanguage());
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
            category = categoryService.get(id);
        }
    }

    public void validateCreate() {
        long cnt = categoryService.countByDuplicate(category);
        if (cnt > 0) {
            String msg = getText("validate.duplicate.2", new String[]{getText(TEXT_CATEGORY_NAME),
                    getText(TEXT_CATEGORY_TYPE)});
            addActionError(msg);
        }
    }

    public void validateUpdate() {
        validateCreate();
    }

    protected List<CategoryView> formatList(List<Category> list) {
        List<CategoryView> listView = new ArrayList<CategoryView>();
        for (Category lgn : list) {
            listView.add(formatItem(lgn));
        }
        return listView;
    }

    protected CategoryView formatItem(Category category) {
        CategoryView view = new CategoryView();
        BeanUtils.copyProperties(category, view);

        view.setTypeName(referanceService.getText(ItemConst.NAME_CATEGORY_TYPE, category.getType(), getLanguage()));

        return view;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CategorySearch getSearch() {
        return search;
    }

    public void setSearch(CategorySearch search) {
        this.search = search;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<CategoryView> getListCategory() {
        return listCategory;
    }

    public void setListCategory(List<CategoryView> listCategory) {
        this.listCategory = listCategory;
    }

    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public List<Item> getListReferanceCategoryType() {
        return listReferanceCategoryType;
    }

    public void setListReferanceCategoryType(List<Item> listReferanceCategoryType) {
        this.listReferanceCategoryType = listReferanceCategoryType;
    }

    public void setReferanceService(ReferanceService referanceService) {
        this.referanceService = referanceService;
    }
}
