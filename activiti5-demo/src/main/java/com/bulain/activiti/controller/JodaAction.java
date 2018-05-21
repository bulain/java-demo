package com.bulain.activiti.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import com.bulain.activiti.model.Joda;
import com.bulain.activiti.pojo.JodaSearch;
import com.bulain.activiti.pojo.JodaView;
import com.bulain.activiti.service.JodaService;
import com.bulain.common.controller.PageSupportActionSupport;

public class JodaAction extends PageSupportActionSupport {
    private static final String TEXT_JODA_MODEL = "joda.model";
    private static final long serialVersionUID = -985235699430511943L;
    private static final Logger LOG = LoggerFactory.getLogger(JodaAction.class);

    private Long id;

    private JodaSearch search;
    private Joda joda;
    private List<JodaView> listJoda;

    private transient JodaService jodaService;

    public String list() {
        search = (JodaSearch) getSearchFromSession(JodaSearch.class, search);
        page = getPageFromSession();

        List<Joda> list = jodaService.page(search, page);
        listJoda = formatList(list);

        putSearchToSession(JodaSearch.class, search);
        putPageToSession();

        return SUCCESS;
    }

    public String newn() {
        joda = new Joda();
        return SUCCESS;
    }
    public String create() {
        try {
            jodaService.insert(joda);
            String msg = getText("info.create", new String[]{getText(TEXT_JODA_MODEL)});
            addActionMessage(msg);
        } catch (Exception e) {
            LOG.error("create()", e);
            String msg = getText("error.create", new String[]{getText(TEXT_JODA_MODEL)});
            addActionError(msg);
            return ERROR;
        }
        return SUCCESS;
    }
    public String show() {
        Joda temp = jodaService.get(id);
        joda = formatItem(temp);
        return SUCCESS;
    }
    public String edit() {
        joda = jodaService.get(id);
        return SUCCESS;
    }
    public String update() {
        try {
            jodaService.update(joda, true);
            String msg = getText("info.update", new String[]{getText(TEXT_JODA_MODEL)});
            addActionMessage(msg);
        } catch (Exception e) {
            LOG.error("update()", e);
            String msg = getText("error.update", new String[]{getText(TEXT_JODA_MODEL)});
            addActionError(msg);
            return ERROR;
        }
        return SUCCESS;
    }
    public String destroy() {
        try {
            jodaService.delete(id);
            String msg = getText("info.delete", new String[]{getText(TEXT_JODA_MODEL)});
            addActionMessage(msg);
        } catch (Exception e) {
            LOG.error("destroy()", e);
            String msg = getText("error.delete", new String[]{getText(TEXT_JODA_MODEL)});
            addActionError(msg);
            return ERROR;
        }
        return SUCCESS;
    }

    public void prepareUpdate() {
        if (id != null) {
            joda = jodaService.get(id);
        }
    }

    protected List<JodaView> formatList(List<Joda> list) {
        List<JodaView> listView = new ArrayList<JodaView>();
        for (Joda item : list) {
            listView.add(formatItem(item));
        }
        return listView;
    }

    protected JodaView formatItem(Joda joda) {
        JodaView view = new JodaView();
        BeanUtils.copyProperties(joda, view);
        return view;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public JodaSearch getSearch() {
        return search;
    }

    public void setSearch(JodaSearch search) {
        this.search = search;
    }

    public Joda getJoda() {
        return joda;
    }

    public void setJoda(Joda joda) {
        this.joda = joda;
    }

    public List<JodaView> getListJoda() {
        return listJoda;
    }

    public void setListJoda(List<JodaView> listJoda) {
        this.listJoda = listJoda;
    }

    public void setJodaService(JodaService jodaService) {
        this.jodaService = jodaService;
    }

}
