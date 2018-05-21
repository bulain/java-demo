package com.bulain.jbpm4order.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bulain.common.model.Constants;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class AuthenticateInterceptor extends MethodFilterInterceptor {
    private static final long serialVersionUID = 2452507695120929898L;
    private static final Logger LOG = LoggerFactory.getLogger(AuthenticateInterceptor.class);

    private static final String INVALID_AUTHENTICATE_CODE = "invalid.authenticate";
    private static final String AUTHENTICATE = "/authenticate/";

    @Override
    protected String doIntercept(ActionInvocation invocation) throws Exception {
        LOG.debug("Intercepting invocation to check for valid authenticate.");

        HttpSession session = ServletActionContext.getRequest().getSession(true);

        synchronized (session) {
            if (!validAuthenticate()) {
                return handleInvalidAuthenticate(invocation);
            }

            return handleValidAuthenticate(invocation);
        }
    }

    protected boolean validAuthenticate() {
        HttpServletRequest request = ServletActionContext.getRequest();
        Map<String, Object> session = ActionContext.getContext().getSession();

        String requestURI = request.getRequestURI();
        String contextPath = request.getContextPath();

        boolean bool = requestURI.startsWith(contextPath + AUTHENTICATE)
                || session.get(Constants.SESSION_KEY_LOGIN) != null;

        return bool;
    }

    protected String handleValidAuthenticate(ActionInvocation invocation) throws Exception {
        return invocation.invoke();
    }

    protected String handleInvalidAuthenticate(ActionInvocation invocation) throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        Map<String, Object> session = ActionContext.getContext().getSession();

        String requestURI = request.getRequestURI();
        String contextPath = request.getContextPath();

        session.put(Constants.SESSION_KEY_REQUEST_URI, requestURI.replaceFirst(contextPath, ""));

        return INVALID_AUTHENTICATE_CODE;
    }
}
