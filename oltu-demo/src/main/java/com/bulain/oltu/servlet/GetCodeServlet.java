package com.bulain.oltu.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bulain.oltu.util.Constant;

public class GetCodeServlet extends HttpServlet {
    private static final long serialVersionUID = 3389922058147805223L;
    private static final Logger logger = LoggerFactory.getLogger(GetCodeServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String locationUri = Constant.OAUTH_LOCATION_URI;
        OAuthClientRequest authRequest;
        try {
            authRequest = OAuthClientRequest.authorizationProvider(Constant.OAUTH_PROVIDER_TYPE)
                    .setClientId(Constant.OAUTH_CLIENT_ID).setRedirectURI(Constant.OAUTH_REDIRECT_URI)
                    .buildQueryMessage();
            locationUri = authRequest.getLocationUri();
        } catch (OAuthSystemException ex) {
            logger.error("doGet(HttpServletRequest, HttpServletResponse)", ex);
        }
        response.sendRedirect(locationUri);
    }
}
