package com.bulain.zk.vm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;

import com.bulain.common.model.Person;
import com.bulain.common.pojo.PersonSearch;
import com.bulain.common.pojo.PersonView;
import com.bulain.common.service.PersonService;

@VariableResolver(DelegatingVariableResolver.class)
public class PopupPersonVm extends PageSupportVm {
    private static final long serialVersionUID = 1878177264720923863L;

    private PersonSearch search;
    private List<PersonView> listPerson;
    private Person person;

    @WireVariable
    private PersonService personService;

    @Init
    public void init() {
        search = new PersonSearch();
        Map<?, ?> arg = Executions.getCurrent().getArg();
        search.setFirstName((String) arg.get("name"));
        globalCmd = (String) arg.get("globalCmd");
        Object quick = arg.get("quick");

        list();

        if (quick != null && listPerson.size() == 1) {
            PersonView p = listPerson.get(0);
            Map<String, Object> args = new HashMap<String, Object>();
            args.put("person", p);
            BindUtils.postGlobalCommand(null, null, globalCmd, args);
        }
    }

    @Command
    @NotifyChange({"listView", "search"})
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

    @Command
    @NotifyChange({"listView", "search"})
    public void paging() {
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

}
