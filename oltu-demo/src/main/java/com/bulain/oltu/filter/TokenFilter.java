package com.bulain.oltu.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

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

import com.bulain.oltu.util.Constant;

public class TokenFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(TokenFilter.class);
    
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession();
        Object accessToken = session.getAttribute(Constant.P_ACCESS_TOKEN);

        String requestURI = httpRequest.getRequestURI();
        List<String> lists = Arrays.asList(new String[]{Constant.PAGE_INDEX, Constant.PAGE_GET_CODE,
                Constant.PAGE_VALIDATE_CODE});

        logger.debug("RequestURI: " + requestURI);
        
        if (accessToken == null && !lists.contains(requestURI)) {
            httpResponse.sendRedirect(Constant.OAUTH_LOCATION_URI);
        } else {
            chain.doFilter(request, response);
        }
    }

    public void destroy() {

    }

}
