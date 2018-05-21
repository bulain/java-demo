package com.bulain.common.servlet;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bulain.common.log4j.Log4jDailyRollingContext;

public class Log4jDailyRollingFileFilter extends Log4jFilter {
    private static final Logger LOG = LoggerFactory.getLogger(Log4jDailyRollingFileFilter.class);

    public void init(FilterConfig filterConfig) throws ServletException {

    }
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {

        boolean excludeUrl = excludeUrl((HttpServletRequest) request);
        if (excludeUrl) {
            chain.doFilter(request, response);
            return;
        }

        Log4jDailyRollingContext.clear();

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpSession session = httpServletRequest.getSession(true);
        String format = String.format("======================== %s Start", session.getId());
        LOG.info("");
        LOG.info(format);

        try {
            chain.doFilter(request, response);
        } finally {
            format = String.format("======================== %s End", session.getId());
            LOG.info(format);
            LOG.info("");
            Log4jDailyRollingContext.flush();
        }
    }

    public void destroy() {
    }

}
