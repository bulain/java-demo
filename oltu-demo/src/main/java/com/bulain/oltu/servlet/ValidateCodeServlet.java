package com.bulain.oltu.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.GitHubTokenResponse;
import org.apache.oltu.oauth2.common.OAuthProviderType;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.types.GrantType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bulain.oltu.util.Constant;

public class ValidateCodeServlet extends HttpServlet {
    private static final long serialVersionUID = 3389922058147805223L;
    private static final Logger logger = LoggerFactory.getLogger(ValidateCodeServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String locationUri = Constant.OAUTH_LOCATION_URI;
        String code = request.getParameter(Constant.P_CODE);
        OAuthClientRequest authRequest;
        try {
            authRequest = OAuthClientRequest.tokenProvider(OAuthProviderType.GITHUB)
                    .setGrantType(GrantType.AUTHORIZATION_CODE).setClientId(Constant.OAUTH_CLIENT_ID)
                    .setClientSecret(Constant.OAUTH_SECRET).setRedirectURI(Constant.OAUTH_REDIRECT_URI).setCode(code)
                    .buildBodyMessage();

            OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());
            GitHubTokenResponse oAuthResponse = oAuthClient.accessToken(authRequest, GitHubTokenResponse.class);
            String accessToken = oAuthResponse.getAccessToken();
            HttpSession session = request.getSession();
            session.setAttribute(Constant.P_ACCESS_TOKEN, accessToken);
            locationUri = Constant.PAGE_SUCCESS;
        } catch (OAuthSystemException ex) {
            logger.error("doGet(HttpServletRequest, HttpServletResponse)", ex);
        } catch (OAuthProblemException e) {
            logger.error("doGet(HttpServletRequest, HttpServletResponse)", e);
        }
        response.sendRedirect(locationUri);
    }
}
