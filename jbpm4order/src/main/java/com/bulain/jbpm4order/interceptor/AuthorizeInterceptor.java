package com.bulain.jbpm4order.interceptor;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;

import com.bulain.common.model.Constants;
import com.bulain.common.service.AuthorizeService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class AuthorizeInterceptor extends MethodFilterInterceptor {
    private static final long serialVersionUID = 7280156919319137925L;
    private static final Logger LOG = LoggerFactory.getLogger(AuthorizeInterceptor.class);

    private static final String INVALID_AUTHORIZE_CODE = "invalid.authorize";
    private static final String AUTHENTICATE = "/authenticate/";
    private static final String AUTHORIZE_SERVICE_NAME = "authorizeService";

    @Override
    protected String doIntercept(ActionInvocation invocation) throws Exception {
        LOG.debug("Intercepting invocation to check for valid authorize.");

        HttpSession session = ServletActionContext.getRequest().getSession(true);

        synchronized (session) {
            if (!validAuthorize()) {
                return handleInvalidAuthorize(invocation);
            }

            return handleValidAuthorize(invocation);
        }
    }

    protected boolean validAuthorize() {
        HttpServletRequest request = ServletActionContext.getRequest();
        Map<String, Object> session = ActionContext.getContext().getSession();

        String requestURI = request.getRequestURI();
        String contextPath = request.getContextPath();

        if (requestURI.startsWith(contextPath + AUTHENTICATE)) {
            return true;
        }

        ServletContext servletContext = ServletActionContext.getServletContext();
        ApplicationContext applicationContext = (ApplicationContext) servletContext
                .getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
        AuthorizeService authorizeService = (AuthorizeService) applicationContext.getBean(AUTHORIZE_SERVICE_NAME);

        String[] split = requestURI.split("/");

        int size = contextPath.length() <= 0 ? 3 : 4;
        int indexController = contextPath.length() <= 0 ? 1 : 2;
        int indexAction = contextPath.length() <= 0 ? 2 : 3;

        if (split.length < size) {
            return false;
        }

        String controller = split[indexController];
        String action = split[indexAction].split("\\.")[0];
        String permission = authorizeService.getPermission(controller, action);
        if (permission == null) {
            return true;
        }

        @SuppressWarnings("unchecked")
        List<String> listPermission = (List<String>) session.get(Constants.SESSION_KEY_AUTHORIZE);
        if (listPermission.contains(permission)) {
            return true;
        }

        return false;
    }

    protected String handleValidAuthorize(ActionInvocation invocation) throws Exception {
        return invocation.invoke();
    }

    protected String handleInvalidAuthorize(ActionInvocation invocation) throws Exception {
        return INVALID_AUTHORIZE_CODE;
    }
}
