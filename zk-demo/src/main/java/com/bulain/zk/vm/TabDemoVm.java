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

import com.bulain.common.model.Person;
import com.bulain.common.page.Page;
import com.bulain.common.pojo.PersonSearch;
import com.bulain.common.pojo.PersonView;
import com.bulain.common.service.PersonService;

@VariableResolver(DelegatingVariableResolver.class)
public class TabDemoVm implements Serializable {
    private static final long serialVersionUID = 6650982848066638271L;

    private PersonSearch search;
    private Page page = new Page();
    private List<PersonView> listView;
    private PersonView view;

    @WireVariable
    private PersonService personService;

    @Wire("#tabList")
    private Tab tabList;
    @Wire("#tabDetail")
    private Tab tabDetail;
    @AfterCompose
    public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
        Selectors.wireComponents(view, this, false);
    }

    @Init
    public void init() {
        search = new PersonSearch();
        page = new Page();
        view = new PersonView(new Person());
        doList();
    }

    @Command
    @NotifyChange({"search"})
    public void resetSearch() {
        search = new PersonSearch();
    }

    private void doList() {
        List<Person> list = personService.page(search, page);
        listView = formatItemList(list);
        if (!listView.contains(view)) {
            view = new PersonView(new Person());
        }
    }

    @Command
    @GlobalCommand("global.tabdemo.list")
    @NotifyChange({"listView", "search", "page", "view"})
    public void list() {
        doList();
        tabList.setSelected(true);
    }

    @Command
    @NotifyChange({"listView", "search", "page", "view"})
    public void paging() {
        doList();
    }

    @Command
    @NotifyChange({"listView", "search", "page", "view"})
    public void sort(@BindingParam("orderBy") String orderBy, @BindingParam("ascending") boolean ascending) {
        search.setOrderBy(orderBy);
        search.setSequance(ascending ? "asc" : "desc");
        page.setPage(1);
        doList();
    }

    @Command("new")
    @GlobalCommand("global.tabdemo.new")
    @NotifyChange({"view"})
    public void newn() {
        view = new PersonView(new Person());

        tabDetail.setSelected(true);
        notifyLoginInit(0L);
    }

    @Command
    @NotifyChange({"view"})
    public void resetView() {
    }

    @Command
    @NotifyChange({"listView", "search", "view", "page"})
    public void save() {
        try {
            if (view.getId() == null) {
                personService.insert(view);
            } else {
                personService.update(view, true);
            }
            doList();

            notifyLoginInit(0L);

            Clients.showNotification(Labels.getLabel("info.create", new Object[]{Labels.getLabel("person.model")}));
        } catch (Exception e) {
            Clients.showNotification(Labels.getLabel("error.create", new Object[]{Labels.getLabel("person.model")}));
        }
    }

    @Command
    @GlobalCommand("global.tabdemo.edit")
    @NotifyChange("view")
    public void edit(@BindingParam("id") Long id) {
        Person p = personService.get(id);
        view = formatItem(p);
        notifyLoginInit(id);
    }

    private void notifyLoginInit(Long id) {
        String cmdName = "global.tablogin.init";;
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("personId", id);
        BindUtils.postGlobalCommand(null, null, cmdName, args);
    }

    @Command
    public void navigate() {
        tabDetail.setSelected(true);
    }

    private void destroyDirect(Long id) {
        try {
            personService.delete(id);
            view = new PersonView(new Person());
            doList();

            String[] strs = new String[]{"listView", "search", "view", "page"};
            for (String str : strs) {
                BindUtils.postNotifyChange(null, null, this, str);
            }
            notifyLoginInit(0L);

            Clients.showNotification(Labels.getLabel("info.delete", new Object[]{Labels.getLabel("person.model")}));
        } catch (Exception e) {
            Clients.showNotification(Labels.getLabel("error.delete", new Object[]{Labels.getLabel("person.model")}));
        }
    }

    @Command
    @GlobalCommand("global.tabdemo.destroy")
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
        Messagebox.show("Are you sure you want to delete this person?", "Confirm", Messagebox.YES + Messagebox.NO,
                null, listener);
    }
    
    public void setView(PersonView view) {
        this.view = view;
        notifyLoginInit(view.getId());
    }

    private List<PersonView> formatItemList(List<Person> list) {
        List<PersonView> listView = new ArrayList<PersonView>();
        for (Person p : list) {
            listView.add(formatItem(p));
        }
        return listView;
    }

    private PersonView formatItem(Person person) {
        PersonView view = new PersonView(person);
        return view;
    }

    public PersonSearch getSearch() {
        return search;
    }

    public void setSearch(PersonSearch search) {
        this.search = search;
    }

    public List<PersonView> getListView() {
        return listView;
    }

    public PersonView getView() {
        return view;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    @VariableResolver(DelegatingVariableResolver.class)
    public static class PopupVm implements Serializable {
        private static final long serialVersionUID = 3532722879822639177L;

        @Wire("#gridPopup")
        private Menupopup menupopup;
        @AfterCompose
        public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
            Selectors.wireComponents(view, this, false);
        }

        @Init
        public void init() {
        }

        private Long entityId;

        private String[] itemMenus = new String[]{"New", "Refresh", "----", "Edit", "Delete"};
        private String[] boxMenus = new String[]{"New", "Refresh"};
        private String[][] tables = new String[][]{{"New", "global.tabdemo.new"}, {"Refresh", "global.tabdemo.list"},
                {"Edit", "global.tabdemo.edit"}, {"Delete", "global.tabdemo.destroy"}};
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
                        args.put("id", entityId);
                        BindUtils.postGlobalCommand(null, null, cmdName, args);
                    }
                };
                item.addEventListener("onClick", listener);
            }
        }

        @Command
        public void openPopupMenu(@ContextParam(ContextType.TRIGGER_EVENT) OpenEvent event) {
            if (event.isOpen()) {
                Component reference = event.getReference();
                if (reference instanceof Listitem) {
                    Listitem item = (Listitem) reference;
                    PersonView view = item.getValue();
                    entityId = view.getId();
                    for (String label : itemMenus) {
                        addMenu(menupopup, label);
                    }
                } else if (reference instanceof Listbox) {
                    entityId = null;
                    for (String label : boxMenus) {
                        addMenu(menupopup, label);
                    }
                }
            } else {
                entityId = null;
                removeAllMenu(menupopup);
            }
        }
    }
}
