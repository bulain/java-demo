package com.bulain.zk.vm;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.util.Locales;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;

import com.bulain.common.model.Reference;
import com.bulain.common.pojo.Item;
import com.bulain.common.pojo.ItemConst;
import com.bulain.common.pojo.ReferenceSearch;
import com.bulain.common.service.ReferenceService;
import com.bulain.zk.pojo.DemoSearch;
import com.bulain.zk.pojo.DemoView;

@VariableResolver(DelegatingVariableResolver.class)
public class DemoVm implements Serializable {
    private static final long serialVersionUID = 4739484658831184078L;

    @WireVariable
    private transient ReferenceService referenceService;

    private DemoSearch search = new DemoSearch();
    private DemoView view;
    private List<DemoView> listView;

    private List<Item> listReferenceName;
    private List<Item> listReferenceLang;
    private List<Item> listReferenceCatagory;

    @Init
    public void init() {
        prepare();
        doList();
    }

    @Command
    @NotifyChange({"listView", "search"})
    public void list() {
        search.setPage(1);
        doList();
    }
    
    @NotifyChange({"listView", "search"})
    public void setActivePage(int activePage) {
        search.setPage(activePage + 1);
        doList();
    }
    
    @Command
    @NotifyChange({"listView", "search"})
    public void sort(@BindingParam("orderBy") String orderBy, @BindingParam("ascending") boolean ascending) {
        search.setOrderBy(orderBy);
        search.setSequance(ascending ? "asc" : "desc");
        doList();
    }

    private void doList(){
        ReferenceSearch s = searchFrom(search);
        List<Reference> list = referenceService.page(s, search);
        listView = toView(list);
    }

    @Command("new")
    @NotifyChange({"view"})
    public void newn() {
        view = new DemoView();
    }

    @Command
    @NotifyChange({"listView", "search", "view"})
    public void save() {
        try {
            Reference bean = fromView(view);
            if (bean.getId() == null) {
                referenceService.insert(bean);
            } else {
                referenceService.update(bean, false);
            }
            view = null;
            list();
            Clients.showNotification(Labels.getLabel("info.create", new Object[]{Labels.getLabel("reference.model")}));
        } catch (Exception e) {
            Clients.showNotification(Labels.getLabel("error.create", new Object[]{Labels.getLabel("reference.model")}));
        }
    }

    @Command
    @NotifyChange({"view"})
    public void show(@BindingParam("id") Long id) {
        Reference temp = referenceService.get(id);
        view = toView(temp);
    }

    @Command
    @NotifyChange({"view"})
    public void edit(@BindingParam("id") Long id) {
        Reference temp = referenceService.get(id);
        view = toView(temp);
    }

    @Command
    @NotifyChange({"listView", "search", "view"})
    public void destroy(@BindingParam("id") Long id) {
        try {
            referenceService.delete(id);
            if (view != null && id.equals(view.getId())) {
                view = null;
            }
            list();
            Clients.showNotification(Labels.getLabel("info.delete", new Object[]{Labels.getLabel("reference.model")}));
        } catch (Exception e) {
            Clients.showNotification(Labels.getLabel("error.delete", new Object[]{Labels.getLabel("reference.model")}));
        }
    }

    private void prepare() {
        listReferenceName = referenceService.findItem(ItemConst.NAME_REFERANCE, getLanguage());
        listReferenceLang = referenceService.findItem(ItemConst.NAME_LANGUAGE, getLanguage());
        listReferenceCatagory = referenceService.findItem(ItemConst.NAME_CATAGORY, getLanguage());
    }

    protected String getLanguage() {
        return Locales.getCurrent().getLanguage();
    }

    private ReferenceSearch searchFrom(DemoSearch search) {
        ReferenceSearch s = new ReferenceSearch();
        s.setName(search.getNameItem().getKey());
        s.setCode(search.getCode());
        s.setLang(search.getLangItem().getKey());
        s.setCatagory(search.getCatagoryItem().getKey());
        s.setOrderBy(search.getOrderBy());
        s.setSequance(search.getSequance());
        return s;
    }

    protected List<DemoView> toView(List<Reference> list) {
        List<DemoView> listView = new ArrayList<DemoView>();
        for (Reference d : list) {
            listView.add(toView(d));
        }
        return listView;
    }

    protected DemoView toView(Reference d) {
        DemoView dv = new DemoView(d);
        dv.setNameItem(referenceService.getItem(ItemConst.NAME_REFERANCE, dv.getName(), getLanguage()));
        dv.setLangItem(referenceService.getItem(ItemConst.NAME_LANGUAGE, dv.getLang(), getLanguage()));
        dv.setCatagoryItem(referenceService.getItem(ItemConst.NAME_CATAGORY, dv.getCatagory(), getLanguage()));
        return dv;
    }

    protected Reference fromView(DemoView dv) {
        dv.setName(dv.getNameItem().getKey());
        dv.setLang(dv.getLangItem().getKey());
        dv.setCatagory(dv.getCatagoryItem().getKey());
        return dv;
    }

    public DemoSearch getSearch() {
        return search;
    }

    public void setSearch(DemoSearch search) {
        this.search = search;
    }

    public DemoView getView() {
        return view;
    }

    public void setView(DemoView view) {
        this.view = view;
    }

    public List<DemoView> getListView() {
        return listView;
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
