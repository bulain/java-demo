package com.bulain.common.jsp;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;

import org.apache.struts2.views.jsp.TagUtils;

import com.opensymphony.xwork2.util.ValueStack;

public class TextareaTagSupport extends AbstractTextTagSupport {
    private static final long serialVersionUID = -4855902415028843359L;

    public TextareaTagSupport() {
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
            if (str == null) {
                str = "";
            }
            str = prepare(str);
            str = str.replaceAll("\n", "<br/>");
            pageContext.getOut().write(str);
        } catch (IOException ex) {
            throw new JspTagException("TextareaTagSupport: " + ex.getMessage());
        }
        return SKIP_BODY;
    }

}

