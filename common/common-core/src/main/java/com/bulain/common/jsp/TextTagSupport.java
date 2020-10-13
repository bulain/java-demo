package com.bulain.common.jsp;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;

import org.apache.struts2.views.jsp.TagUtils;

import com.opensymphony.xwork2.util.ValueStack;

public class TextTagSupport extends AbstractTextTagSupport {
    private static final long serialVersionUID = -4855902415028843359L;
    private static final int DEFAULT_LENGTH = 50;

    private int length;

    public TextTagSupport() {
        length = DEFAULT_LENGTH;
    }

    @Override
    public int doStartTag() throws JspException {
        return EVAL_PAGE;
    }

    @Override
    public int doEndTag() throws JspException {
        ValueStack valueStack = TagUtils.getStack(pageContext);
        String str = (String) valueStack.findValue(value);

        try {
            if (str != null && str.length() > length) {
                str = str.substring(0, length) + "...";
            }
            if (str == null) {
                str = "";
            }
            str = prepare(str);
            str = str.replaceAll("\n", "<br/>");
            pageContext.getOut().write(str);
        } catch (IOException ex) {
            throw new JspTagException("TextTagSupport: " + ex.getMessage());
        }
        return SKIP_BODY;
    }

    public int getLength() {
        return length;
    }
    
    public void setLength(int length) {
        this.length = length;
    }
    
}
