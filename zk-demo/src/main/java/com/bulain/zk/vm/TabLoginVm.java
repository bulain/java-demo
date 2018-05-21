package com.bulain.zk.vm;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.util.Locales;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.MouseEvent;
import org.zkoss.zk.ui.event.OpenEvent;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Menuitem;
import org.zkoss.zul.Menupopup;
import org.zkoss.zul.Menuseparator;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Tab;

import com.bulain.common.model.Login;
import com.bulain.common.page.Page;
import com.bulain.common.pojo.ItemConst;
import com.bulain.common.pojo.LoginSearch;
import com.bulain.common.pojo.LoginView;
import com.bulain.common.service.LoginService;
import com.bulain.common.service.MasterService;
import com.bulain.common.service.ReferenceService;

@VariableResolver(DelegatingVariableResolver.class)
public class TabLoginVm implements Serializable {
    private static final long serialVersionUID = 8603230110834911025L;

    private LoginSearch search;
    private Page page;
    private LoginView view;
    private List<LoginView> listView;

    @WireVariable
    private transient LoginService loginService;
    @WireVariable
    private transient MasterService masterService;
    @WireVariable
    private transient ReferenceService referenceService;

    @Wire("#tabLoginList")
    private Tab tabLoginList;
    @Wire("#tabLoginDetail")
    private Tab tabLoginDetail;
    @AfterCompose
    public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
        Selectors.wireComponents(view, this, false);
    }

    @Init
    public void init() {
        search = new LoginSearch();
        page = new Page();
        page.setPage(1);
        view = new LoginView(new Login());
        listView = new ArrayList<LoginView>();
    }

    @GlobalCommand("global.tablogin.init")
    @NotifyChange({"listView", "search", "page", "view"})
    public void initVm(@BindingParam("personId") Long personId) {
        search.setPersonId(personId);
        doList();
        tabLoginList.setSelected(true);
    }

    private void doList() {
        List<Login> list = loginService.page(search, page);
        listView = formatList(list);
        if (!listView.contains(view)) {
            view = new LoginView(new Login());
        }
    }

    @Command
    @GlobalCommand("global.tablogin.list")
    @NotifyChange({"listView", "search", "page"})
    public void list() {
        page.setPage(1);
        doList();
        tabLoginList.setSelected(true);
    }

    @Command
    @NotifyChange({"listView", "search", "page"})
    public void paging() {
        doList();
    }

    @Command
    @NotifyChange({"listView", "search", "page"})
    public void sort(@BindingParam("orderBy") String orderBy, @BindingParam("ascending") boolean ascending) {
        search.setOrderBy(orderBy);
        search.setSequance(ascending ? "asc" : "desc");
        page.setPage(1);
        doList();
    }

    @Command("new")
    @GlobalCommand("global.tablogin.new")
    @NotifyChange({"view"})
    public void newn() {
        Login l = new Login();
        view = new LoginView(l);
        tabLoginDetail.setSelected(true);
    }

    @Command
    @NotifyChange({"view"})
    public void resetView() {
    }

    @Command
    @GlobalCommand("global.tablogin.edit")
    @NotifyChange({"view"})
    public void edit(@BindingParam("id") Long id) {
        Login lgn = loginService.get(id);
        view = formatItem(lgn);
    }

    @Command
    public void navigate() {
        tabLoginDetail.setSelected(true);
    }

    @Command
    @NotifyChange({"listView", "search", "view", "page"})
    public void save() {
        try {
            if (view.getId() == null) {
                view.setPersonId(search.getPersonId());
                loginService.insert(view);
            } else {
                loginService.update(view, false);
            }
            doList();
            Clients.showNotification(Labels.getLabel("info.create", new Object[]{Labels.getLabel("login.model")}));
        } catch (Exception e) {
            Clients.showNotification(Labels.getLabel("error.create", new Object[]{Labels.getLabel("login.model")}));
        }
    }

    private void destroyDirect(Long id) {
        try {
            loginService.delete(id);
            view = new LoginView(new Login());
            doList();
            String[] strs = new String[]{"listView", "search", "view"};
            for (String str : strs) {
                BindUtils.postNotifyChange(null, null, this, str);
            }
            Clients.showNotification(Labels.getLabel("info.delete", new Object[]{Labels.getLabel("login.model")}));
        } catch (Exception e) {
            Clients.showNotification(Labels.getLabel("error.delete", new Object[]{Labels.getLabel("login.model")}));
        }
    }

    @Command
    @GlobalCommand("global.tablogin.destroy")
    public void destroy(@BindingParam("id") final Long id) {
        EventListener<Event> listener = new EventListener<Event>() {
            public void onEvent(Event event) throws Exception {
                if (Messagebox.ON_YES.equals(event.getName())) {
                    Map<String, Object> args = new HashMap<String, Object>();
                    args.put("id", id);
                    destroyDirect(id);
                }
            }
        };
        Messagebox.show("Are you sure you want to delete this login?", "Confirm", Messagebox.YES + Messagebox.NO, null,
                listener);
    }

    public void setView(LoginView view) {
        this.view = view;
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
        lgnView.setPersonName(masterService.getValue4Person(lgnView.getPersonId()));
        return lgnView;
    }

    protected String getLanguage() {
        return Locales.getCurrent().getLanguage();
    }

    public LoginView getView() {
        return view;
    }

    public List<LoginView> getListView() {
        return listView;
    }

    public LoginSearch getSearch() {
        return search;
    }

    public void setSearch(LoginSearch search) {
        this.search = search;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    private PopupVm popupVm = new PopupVm();
    public PopupVm getPopupVm() {
        return popupVm;
    }

    @VariableResolver(DelegatingVariableResolver.class)
    public class PopupVm implements Serializable {
        private static final long serialVersionUID = 7287047005715186874L;

        @Wire("#listboxPopup")
        private Menupopup menupopup;
        @AfterCompose
        public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
            Selectors.wireComponents(view, this, false);
        }

        private String[] itemMenus = new String[]{"New", "Refresh", "----", "Edit", "Delete"};
        private String[] boxMenus = new String[]{"New", "Refresh"};

        private Long loginId;

        private String[][] tables = new String[][]{{"New", "global.tablogin.new"}, {"Refresh", "global.tablogin.list"},
                {"Edit", "global.tablogin.edit"}, {"Delete", "global.tablogin.destroy"}};
        private Map<String, String> getMenuMapping() {
            Map<String, String> mapping = new HashMap<String, String>();
            for (String[] line : tables) {
                mapping.put(line[0], line[1]);
            }
            return mapping;
        }

        private void removeAllMenu(Menupopup menupopup) {
            List<Component> children = menupopup.getChildren();
            Iterator<Component> iterator = children.iterator();
            while (iterator.hasNext()) {
                iterator.next();
                iterator.remove();
            }
        }

        private void addMenu(Menupopup menupopup, String label) {
            if ("----".equals(label)) {
                Menuseparator item = new Menuseparator();
                item.setParent(menupopup);
            } else {
                Menuitem item = new Menuitem();
                item.setId("menu" + label);
                item.setLabel(label);
                item.setParent(menupopup);
                item.setValue(label);
                EventListener<MouseEvent> listener = new EventListener<MouseEvent>() {
                    public void onEvent(MouseEvent event) throws Exception {
                        Menuitem item = (Menuitem) event.getTarget();
                        String value = item.getValue();
                        Map<String, String> menuMapping = getMenuMapping();
                        String cmdName = menuMapping.get(value);
                        Map<String, Object> args = new HashMap<String, Object>();
                        args.put("id", loginId);
                        BindUtils.postGlobalCommand(null, null, cmdName, args);
                    }
                };
                item.addEventListener("onClick", listener);
            }
        }

        @Command
        public void openPopupMenu(@ContextParam(ContextType.TRIGGER_EVENT) OpenEvent event) {
            if (enabled()) {
                if (event.isOpen()) {
                    Component reference = event.getReference();
                    if (reference instanceof Listitem) {
                        Listitem listitem = (Listitem) reference;
                        Object value = listitem.getValue();
                        if (value instanceof LoginView) {
                            LoginView view = (LoginView) value;
                            loginId = view.getId();
                            for (String label : itemMenus) {
                                addMenu(menupopup, label);
                            }
                        }
                    } else if (reference instanceof Listbox) {
                        loginId = null;
                        for (String label : boxMenus) {
                            addMenu(menupopup, label);
                        }
                    }
                } else {
                    loginId = null;
                    removeAllMenu(menupopup);
                }
            }
        }

        public boolean enabled() {
            return search.getPersonId() != null && search.getPersonId().compareTo(0L) > 0;
        }
    }
}