package com.bulain.common.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.bulain.common.log4j.Log4jThreadContext;

public class Log4jThreadFilter extends Log4jFilter {

    private static final String DEBUG_NAME = "debug";
    private static final String DEBUG_TRUE = "true";

    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {

        boolean excludeUrl = excludeUrl((HttpServletRequest) request);
        if (excludeUrl) {
            chain.doFilter(request, response);
            return;
        }

        String debug = request.getParameter(DEBUG_NAME);
        if (DEBUG_TRUE.equalsIgnoreCase(debug)) {
            Log4jThreadContext.active();
        }

        try {
            chain.doFilter(request, response);
        } finally {
            if (DEBUG_TRUE.equalsIgnoreCase(debug)) {
                List<String> list = Log4jThreadContext.get();
                PrintWriter writer = response.getWriter();
                writer.print("<div>");
                for (String s : list) {
                    writer.print("<div>");
                    writer.print(s);
                    writer.print("</div>");
                }
                writer.print("</div>");
                writer.flush();
                Log4jThreadContext.clear();
            }
        }
    }

    public void destroy() {
    }

}
