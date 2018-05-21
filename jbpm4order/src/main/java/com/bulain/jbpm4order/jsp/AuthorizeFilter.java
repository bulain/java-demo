package com.bulain.jbpm4order.jsp;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.bulain.common.model.Constants;
import com.bulain.common.service.AuthorizeService;

public class AuthorizeFilter implements Filter {
    private static final Logger LOG = LoggerFactory.getLogger(AuthorizeFilter.class);
    private static final String AUTHENTICATE = "/authenticate/";
    private static final String LOGIN = "login.action";
    private static final String AUTHORIZE_SERVICE_NAME = "authorizeService";

    private ServletContext servletContext;

    public void init(FilterConfig config) throws ServletException {
        servletContext = config.getServletContext();
    }

    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {
        String method = "doFilter(ServletRequest, ServletResponse, FilterChain)";
        
        LOG.debug(method + " - start");

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String requestURI = httpRequest.getRequestURI();
        String contextPath = httpRequest.getContextPath();
        if (requestURI.startsWith(contextPath + AUTHENTICATE)) {
            chain.doFilter(request, response);

            LOG.debug(method + " - end");
            return;
        }

        WebApplicationContext webApplicationContext = WebApplicationContextUtils
                .getWebApplicationContext(servletContext);
        AuthorizeService authorizeService = (AuthorizeService) webApplicationContext.getBean(AUTHORIZE_SERVICE_NAME);

        String[] split = requestURI.split("/");

        if (split.length < 4) {
            httpResponse.sendRedirect(contextPath + AUTHENTICATE + LOGIN);

            LOG.debug(method + " - end");
            return;
        }

        String controller = split[2];
        String action = split[3].split("\\.")[0];
        String permission = authorizeService.getPermission(controller, action);
        if (permission == null) {
            chain.doFilter(request, response);

            LOG.debug(method + " - end");
            return;
        }

        HttpSession session = httpRequest.getSession(true);
        @SuppressWarnings("unchecked")
        List<String> listPermission = (List<String>) session.getAttribute(Constants.SESSION_KEY_AUTHORIZE);
        if (!listPermission.contains(permission)) {
            httpResponse.sendRedirect(contextPath + AUTHENTICATE + LOGIN);

            LOG.debug(method + " - end");
            return;
        }

        chain.doFilter(request, response);

        LOG.debug(method + " - end");
    }
}
