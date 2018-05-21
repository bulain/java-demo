package com.bulain.common.jsp;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.bulain.common.page.OrderBy;


public class OrderTagSupport extends BodyTagSupport {
    private static final long serialVersionUID = -7716684705801512418L;

    private String orderBy;
    private String sequance;
    private String url;
    private String fixOrderBy;
    private String text;

    public OrderTagSupport() {
        orderBy = "orderBy";
        sequance = "sequance";
        url = "";
        text = "";
    }

    @Override
    public int doStartTag() throws JspException {
        OrderBy bean = (OrderBy)pageContext.getRequest().getAttribute("orderBy");
        String ob = bean.getOrderBy();
        String sq = bean.getSequance();

        try {
            StringBuffer sb = new StringBuffer();
            sb.append(url);
            sb.append("?" + orderBy + "=" + fixOrderBy);
            sb.append("&" + sequance + "=");
            sb.append(fixOrderBy.equals(ob) ? ("asc".equals(sq) ? "desc" : "asc") : "asc");

            text = fixOrderBy.equals(ob) ? ("asc".equals(sq) ? "&#9650;" : "&#9660;") : "";

            pageContext.getOut().print("<a href=" + sb.toString() + " >");
        } catch (IOException ex) {
            throw new JspTagException("OrderTagSupport: " + ex.getMessage());
        }

        return EVAL_BODY_INCLUDE;
    }

    @Override
    public int doEndTag() throws JspException {
        try {
            pageContext.getOut().print(text + "</a>");
        } catch (IOException ex) {
            throw new JspTagException("OrderTagSupport: " + ex.getMessage());
        }
        return EVAL_PAGE;
    }

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
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getFixOrderBy() {
        return fixOrderBy;
    }
    public void setFixOrderBy(String fixOrderBy) {
        this.fixOrderBy = fixOrderBy;
    }

}
