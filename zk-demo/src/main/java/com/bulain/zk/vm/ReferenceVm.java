package com.bulain.zk.vm;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;

import com.bulain.common.model.Reference;
import com.bulain.common.pojo.Item;
import com.bulain.common.pojo.ItemConst;
import com.bulain.common.pojo.ReferenceSearch;
import com.bulain.common.pojo.ReferenceView;
import com.bulain.common.service.ReferenceService;

@VariableResolver(DelegatingVariableResolver.class)
public class ReferenceVm extends PageSupportVm {
    private static final long serialVersionUID = -7189214616881847423L;

    private ReferenceSearch search = new ReferenceSearch();
    private Reference reference;
    private List<ReferenceView> listReference;

    @WireVariable
    private transient ReferenceService referenceService;

    private List<Item> listReferenceName ;
    private List<Item> listReferenceLang ;
    private List<Item> listReferenceCatagory;

    @Init
    public void init() {
        prepareList();
        list();
    }

    @Command
    @NotifyChange({"listReference", "page", "activePage"})
    public void sort(@BindingParam("orderBy") String orderBy, @BindingParam("ascending") boolean ascending) {
        search.setOrderBy(orderBy);
        search.setSequance(ascending ? "asc" : "desc");
        list();
    }

    @Command
    @NotifyChange({"listReference", "page", "activePage"})
    public void list() {
        search = (ReferenceSearch) prepareSearch(ReferenceSearch.class, search);
        List<Reference> list = referenceService.page(search, page);
        listReference = formatList(list);
    }

    @Command("new")
    @NotifyChange("reference")
    public void newn() {
        reference = new Reference();
    }

    @Command
    @NotifyChange({"listReference", "reference"})
    public void create() {
        try {
            referenceService.insert(reference);
            list();
            Clients.showNotification("create ok");
        } catch (Exception e) {
            Clients.showNotification("create error");
        }
    }

    @Command
    @NotifyChange({"reference"})
    public void show(@BindingParam("id") Long id) {
        reference = referenceService.get(id);
        reference = formatItem(reference);
    }

    @Command
    @NotifyChange({"reference"})
    public void edit(@BindingParam("id") Long id) {
        reference = referenceService.get(id);
    }

    @Command
    @NotifyChange({"listReference", "reference"})
    public void save() {
        try {
            if (reference.getId() == null) {
                referenceService.insert(reference);
            } else {
                referenceService.update(reference, false);
            }
            reference = null;
            list();
            Clients.showNotification("save ok");
        } catch (Exception e) {
            Clients.showNotification("save error");
        }
    }

    @Command
    @NotifyChange({"listReference", "reference"})
    public void update() {
        try {
            referenceService.update(reference, false);
            reference = null;
            list();
            Clients.showNotification("update ok");
        } catch (Exception e) {
            Clients.showNotification("update error");
        }
    }

    @Command
    @NotifyChange({"listReference", "reference"})
    public void destroy(@BindingParam("id") Long id) {
        try {
            referenceService.delete(id);
            if (reference != null && id.equals(reference.getId())) {
                reference = null;
            }
            list();
            Clients.showNotification("destroy ok");
        } catch (Exception e) {
            Clients.showNotification("destroy error");
        }
    }

    @NotifyChange("listReference")
    public void setActivePage(int activePage) {
        page.setPage(activePage + 1);
        list();
    }

    public void prepareList() {
        listReferenceName = referenceService.findItem(ItemConst.NAME_REFERANCE, getLanguage());
        listReferenceLang = referenceService.findItem(ItemConst.NAME_LANGUAGE, getLanguage());
        listReferenceCatagory = referenceService.findItem(ItemConst.NAME_CATAGORY, getLanguage());
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

    public Reference getReference() {
        return reference;
    }

    public void setReference(Reference reference) {
        this.reference = reference;
    }

    public List<ReferenceView> getListReference() {
        return listReference;
    }

    public List<Item> getListReferenceName() {
        return listReferenceName;
    }

    public List<Item> getListReferenceLang() {
        return listReferenceLang;
    }

    public List<Item> getListReferenceCatagory() {
        return listReferenceCatagory;
    }

}
