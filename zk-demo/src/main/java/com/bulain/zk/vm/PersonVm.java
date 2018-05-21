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
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.MouseEvent;
import org.zkoss.zk.ui.event.OpenEvent;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Menuitem;
import org.zkoss.zul.Menupopup;
import org.zkoss.zul.Menuseparator;
import org.zkoss.zul.Row;

import com.bulain.common.model.Person;
import com.bulain.common.pojo.PersonSearch;
import com.bulain.common.pojo.PersonView;
import com.bulain.common.service.PersonService;

@VariableResolver(DelegatingVariableResolver.class)
public class PersonVm extends PageSupportVm {
    private static final long serialVersionUID = 1878177264720923863L;

    private PersonSearch search;
    private List<PersonView> listPerson;
    private Person person;

    @WireVariable
    private PersonService personService;

    @Init
    public void init() {
        search = (PersonSearch) prepareSearch(PersonSearch.class, search);
        listPerson = new ArrayList<PersonView>();
    }

    @Command
    @NotifyChange("listPerson")
    public void list() {
        search = (PersonSearch) prepareSearch(PersonSearch.class, search);
        List<Person> list = personService.page(search, page);
        listPerson = formatItemList(list);
    }

    @Command
    @NotifyChange({"listView", "search"})
    public void sort(@BindingParam("orderBy") String orderBy, @BindingParam("ascending") boolean ascending) {
        search.setOrderBy(orderBy);
        search.setSequance(ascending ? "asc" : "desc");
        list();
    }

    @Command("new")
    @GlobalCommand("global.person.new")
    @NotifyChange("person")
    public void newn() {
        person = new Person();
    }

    @Command
    @NotifyChange({"listPerson", "person"})
    public void save() {
        try {
            if (person.getId() == null) {
                personService.insert(person);
            } else {
                personService.update(person, true);
            }
            person = null;
            list();
            Clients.showNotification(Labels.getLabel("info.create", new Object[]{Labels.getLabel("person.model")}));
        } catch (Exception e) {
            Clients.showNotification(Labels.getLabel("error.create", new Object[]{Labels.getLabel("person.model")}));
        }
    }

    @Command
    @GlobalCommand("global.person.edit")
    @NotifyChange("person")
    public void edit(@BindingParam("id") Long id) {
        person = personService.get(id);
    }

    @Command
    @GlobalCommand("global.person.destroy")
    @NotifyChange({"listPerson", "person"})
    public void delete(@BindingParam("id") Long id) {
        try {
            personService.delete(id);
            person = null;
            list();
            Clients.showNotification(Labels.getLabel("info.delete", new Object[]{Labels.getLabel("person.model")}));
        } catch (Exception e) {
            Clients.showNotification(Labels.getLabel("error.delete", new Object[]{Labels.getLabel("person.model")}));
        }
    }

    @NotifyChange("listPerson")
    public void setActivePage(int activePage) {
        page.setPage(activePage + 1);
        list();
    }

    private String globalCmd;
    @Command
    public void selectItem(@BindingParam("id") Long id) {
        person = personService.get(id);
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("person", person);
        BindUtils.postGlobalCommand(null, null, globalCmd, args);
    }

    @GlobalCommand
    @NotifyChange({"listPerson", "search"})
    public void globalInitPerson(@BindingParam("name") String name, @BindingParam("globalCmd") String globalCmd) {
        search = new PersonSearch();
        search.setFirstName(name);
        this.globalCmd = globalCmd;
        list();
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

    public List<PersonView> getListPerson() {
        return listPerson;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
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

        private Long personId;

        private String[] itemMenus = new String[]{"New", "Refresh", "----", "Edit", "Delete"};
        private String[] boxMenus = new String[]{"New", "Refresh"};
        private String[][] tables = new String[][]{{"New", "global.person.new"}, {"Refresh", "global.person.new"},
                {"Edit", "global.person.edit"}, {"Delete", "global.person.destroy"}};
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
                        args.put("id", personId);
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
                if (reference instanceof Row) {
                    Row row = (Row) reference;
                    PersonView view = row.getValue();
                    personId = view.getId();
                    for (String label : itemMenus) {
                        addMenu(menupopup, label);
                    }
                } else if (reference instanceof Grid) {
                    personId = null;
                    for (String label : boxMenus) {
                        addMenu(menupopup, label);
                    }
                }
            } else {
                personId = null;
                removeAllMenu(menupopup);
            }
        }
    }
}
