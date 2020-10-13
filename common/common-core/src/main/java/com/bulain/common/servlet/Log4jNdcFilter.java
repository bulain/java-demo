package com.bulain.common.servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.NDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Log4jNdcFilter implements Filter {
    private static final Logger LOG = LoggerFactory.getLogger(Log4jNdcFilter.class);

    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {
        LOG.debug("doFilter(ServletRequest, ServletResponse, FilterChain) - start");

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpSession session = httpServletRequest.getSession(true);
        NDC.push(session.getId());

        try {
            chain.doFilter(request, response);
        } finally {
            NDC.pop();
        }

        LOG.debug("doFilter(ServletRequest, ServletResponse, FilterChain) - end");
    }

    public void destroy() {
    }

}
