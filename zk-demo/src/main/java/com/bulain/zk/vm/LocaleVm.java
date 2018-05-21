package com.bulain.zk.vm;

import java.io.Serializable;
import java.util.Locale;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;

@VariableResolver(DelegatingVariableResolver.class)
public class LocaleVm implements Serializable {
    private static final long serialVersionUID = -3639636829180924613L;

    @WireVariable
    private Session session;
    @WireVariable
    private Execution execution;

    @GlobalCommand
    public void changeLocale(@BindingParam("locale") String locale) {
        Locale preferLocale = locale.length() > 2
                ? new Locale(locale.substring(0, 2), locale.substring(3))
                : new Locale(locale);
        session.setAttribute(org.zkoss.web.Attributes.PREFERRED_LOCALE, preferLocale);
        session.setAttribute("locale", preferLocale);
        execution.sendRedirect("");
    }
}
