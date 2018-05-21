package com.bulain.jbpm4order.jsp;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bulain.common.model.Constants;

public class AuthenticateFilter implements Filter {
    private static final Logger LOG = LoggerFactory.getLogger(AuthenticateFilter.class);
    private static final String AUTHENTICATE = "/authenticate/";
    private static final String LOGIN = "login.action";

    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {
        LOG.debug("doFilter(ServletRequest, ServletResponse, FilterChain) - start");

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String requestURI = httpRequest.getRequestURI();
        String contextPath = httpRequest.getContextPath();
        if (requestURI.startsWith(contextPath + AUTHENTICATE)) {
            chain.doFilter(request, response);

            LOG.debug("doFilter(ServletRequest, ServletResponse, FilterChain) - end");
            return;
        }

        HttpSession session = httpRequest.getSession(true);
        if (session.getAttribute(Constants.SESSION_KEY_LOGIN) != null) {
            chain.doFilter(request, response);

            LOG.debug("doFilter(ServletRequest, ServletResponse, FilterChain) - end");
            return;
        }

        session.setAttribute(Constants.SESSION_KEY_REQUEST_URI, requestURI.replaceFirst(contextPath, ""));
        httpResponse.sendRedirect(contextPath + AUTHENTICATE + LOGIN);

        LOG.debug("doFilter(ServletRequest, ServletResponse, FilterChain) - end");
    }
}
