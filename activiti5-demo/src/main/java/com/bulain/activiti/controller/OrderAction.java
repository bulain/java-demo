package com.bulain.activiti.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import com.bulain.activiti.bpo.OrderBpo;
import com.bulain.activiti.model.Order;
import com.bulain.activiti.pojo.OrderSearch;
import com.bulain.activiti.pojo.OrderView;
import com.bulain.activiti.service.OrderService;
import com.bulain.common.controller.PageSupportActionSupport;

public class OrderAction extends PageSupportActionSupport {
    private static final String TEXT_ORDER_MODEL = "order.model";
    private static final long serialVersionUID = -1592145738956326742L;
    private static final Logger LOG = LoggerFactory.getLogger(OrderAction.class);

    private Long id;
    private String taskId;
    private String submit;

    private OrderSearch search;
    private Order order;
    private List<OrderView> listOrder;

    private transient OrderService orderService;
    private transient OrderBpo orderBpo;

    public String list() {
        search = (OrderSearch) getSearchFromSession(OrderSearch.class, search);
        page = getPageFromSession();

        List<Order> list = orderService.page(search, page);
        listOrder = formatList(list);

        putSearchToSession(OrderSearch.class, search);
        putPageToSession();

        return SUCCESS;
    }

    public String newn() {
        order = new Order();
        return SUCCESS;
    }
    public String create() {
        try {
            orderService.insert(order);
            String msg = getText("common.createInfo", new String[]{TEXT_ORDER_MODEL});
            addActionMessage(msg);
        } catch (Exception e) {
            LOG.error("create()", e);
            String msg = getText("common.createError", new String[]{TEXT_ORDER_MODEL});
            addActionError(msg);
            return ERROR;
        }
        return SUCCESS;
    }
    public String show() {
        Order temp = orderService.get(id);
        order = formatItem(temp);
        return SUCCESS;
    }
    public String edit() {
        order = orderService.get(id);
        return SUCCESS;
    }
    public String update() {
        try {
            orderService.update(order, true);
            String msg = getText("common.updateInfo", new String[]{TEXT_ORDER_MODEL});
            addActionMessage(msg);
        } catch (Exception e) {
            LOG.error("update()", e);
            String msg = getText("common.updateError", new String[]{TEXT_ORDER_MODEL});
            addActionError(msg);
            return ERROR;
        }
        return SUCCESS;
    }
    public String destroy() {
        try {
            orderService.delete(id);
            String msg = getText("common.deleteInfo", new String[]{TEXT_ORDER_MODEL});
            addActionMessage(msg);
        } catch (Exception e) {
            LOG.error("destroy()", e);
            String msg = getText("common.deleteError", new String[]{TEXT_ORDER_MODEL});
            addActionError(msg);
            return ERROR;
        }
        return SUCCESS;
    }

    // workflow
    public String request() {
        order = orderBpo.claim(taskId, "bulain");

        return SUCCESS;
    }

    public String submitRequest() {
        try {
            if ("Request".equals(submit)) {
                order.setWfStatus("Requested");
                Map<String, Object> variables = new HashMap<String, Object>();
                orderBpo.complete(order, taskId, variables);
            } else {
                if (order.getId() == null) {
                    orderService.insert(order);
                } else {
                    orderService.update(order, false);
                }
            }
        } catch (Exception e) {
            LOG.error("submitRequest()", e);
            String msg = getText("common.updateError", new String[]{TEXT_ORDER_MODEL});
            addActionError(msg);
            return ERROR;
        }

        return SUCCESS;
    }

    public String approve() {
        order = orderBpo.claim(taskId, "bulain");

        return SUCCESS;
    }

    public String submitApprove() {
        try {
            order.setWfStatus("Approve".equals(submit) ? "Approved" : "Rejected");

            Map<String, Object> variables = new HashMap<String, Object>();
            variables.put("action", "Approve".equals(submit) ? "approve" : "reject");

            orderBpo.complete(order, taskId, variables);
        } catch (Exception e) {
            LOG.error("submitApprove()", e);
            String msg = getText("common.updateError", new String[]{TEXT_ORDER_MODEL});
            addActionError(msg);
            return ERROR;
        }

        return SUCCESS;
    }

    public void prepareUpdate() {
        if (id != null) {
            order = orderService.get(id);
        }
    }

    public void prepareSubmitRequest() {
        if (order != null && order.getId() != null) {
            order = orderService.get(order.getId());
        }
    }

    public void prepareSubmitApprove() {
        if (order != null && order.getId() != null) {
            order = orderService.get(order.getId());
        }
    }

    protected List<OrderView> formatList(List<Order> list) {
        List<OrderView> listView = new ArrayList<OrderView>();
        for (Order item : list) {
            listView.add(formatItem(item));
        }
        return listView;
    }

    protected OrderView formatItem(Order order) {
        OrderView view = new OrderView();
        BeanUtils.copyProperties(order, view);
        return view;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrderSearch getSearch() {
        return search;
    }

    public void setSearch(OrderSearch search) {
        this.search = search;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public List<OrderView> getListOrder() {
        return listOrder;
    }

    public void setListOrder(List<OrderView> listOrder) {
        this.listOrder = listOrder;
    }

    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }
    public String getSubmit() {
        return submit;
    }
    public void setSubmit(String submit) {
        this.submit = submit;
    }
    public String getTaskId() {
        return taskId;
    }
    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }
    public void setOrderBpo(OrderBpo orderBpo) {
        this.orderBpo = orderBpo;
    }
}
