package com.bulain.jbpm4order.controller;

import java.util.List;
import java.util.Map;

import org.apache.struts2.dispatcher.DefaultActionSupport;

import com.bulain.common.model.Constants;
import com.bulain.common.model.Login;
import com.bulain.common.service.LoginService;
import com.opensymphony.xwork2.ActionContext;

public class AuthenticateAction extends DefaultActionSupport {
    private static final long serialVersionUID = 4472838756641897324L;
    private static final String DEFAULT_URL = "/login/list.action";

    private String redirect;
    private Login login;

    private transient LoginService loginService;

    public String login() {
        login = new Login();
        return SUCCESS;
    }

    public String logon() {
        Login login2 = loginService.getLogin(login.getLoginName(), login.getHashedPassword());
        if (login2 != null) {
            Map<String, Object> session = ActionContext.getContext().getSession();
            session.put(Constants.SESSION_KEY_LOGIN, login2);

            List<String> listAuthorize = loginService.findPermission(login2.getId());
            session.put(Constants.SESSION_KEY_AUTHORIZE, listAuthorize);

            if (redirect == null) {
                redirect = (String) session.get(Constants.SESSION_KEY_REQUEST_URI);
            }
            if (redirect == null) {
                redirect = DEFAULT_URL;
            }
            session.remove(Constants.SESSION_KEY_REQUEST_URI);

            return SUCCESS;
        }
        addActionMessage(getText("login.error.cantlogin"));
        return ERROR;
    }

    public String logout() {
        Map<String, Object> session = ActionContext.getContext().getSession();
        session.remove(Constants.SESSION_KEY_LOGIN);

        addActionMessage(getText("login.info.havelogout"));
        return SUCCESS;
    }

    public String getRedirect() {
        return redirect;
    }
    public void setRedirect(String redirect) {
        this.redirect = redirect;
    }
    public Login getLogin() {
        return login;
    }
    public void setLogin(Login login) {
        this.login = login;
    }
    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }
}
