package com.bulain.zk.pojo;

import com.bulain.common.page.Page;

public class PagedSearch extends Page {
    private static final long serialVersionUID = -5456300791071363078L;

    private String orderBy;
    private String sequance;

    public String getOrderBy() {
        return orderBy;
    }
    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }
    public String getSequance() {
        return sequance;
    }
    public void setSequance(String sequance) {
        this.sequance = sequance;
    }

}
