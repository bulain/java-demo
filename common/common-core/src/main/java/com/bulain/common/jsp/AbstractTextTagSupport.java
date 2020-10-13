package com.bulain.common.jsp;

import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.commons.lang.StringEscapeUtils;

public class AbstractTextTagSupport extends BodyTagSupport {
    private static final long serialVersionUID = 1278232188720492459L;

    protected boolean escapeHtml = true;
    protected boolean escapeJavaScript = false;
    protected boolean escapeXml = false;
    protected boolean escapeCsv = false;
    protected String value;

    protected String prepare(String value) {
        String result = value;
        if (escapeHtml) {
            result = StringEscapeUtils.escapeHtml(result);
        }
        if (escapeJavaScript) {
            result = StringEscapeUtils.escapeJavaScript(result);
        }
        if (escapeXml) {
            result = StringEscapeUtils.escapeXml(result);
        }
        if (escapeCsv) {
            result = StringEscapeUtils.escapeCsv(result);
        }

        return result;
    }

    public void setEscapeHtml(boolean escape) {
        this.escapeHtml = escape;
    }

    public void setEscapeJavaScript(boolean escapeJavaScript) {
        this.escapeJavaScript = escapeJavaScript;
    }

    public void setEscapeCsv(boolean escapeCsv) {
        this.escapeCsv = escapeCsv;
    }

    public void setEscapeXml(boolean escapeXml) {
        this.escapeXml = escapeXml;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
