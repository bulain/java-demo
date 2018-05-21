package com.bulain.oauth.ctrl;

import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bulain.oauth.pojo.AuthResp;
import com.bulain.oauth.pojo.BaseResp;
import com.bulain.oauth.pojo.Deviceresp;
import com.bulain.oauth.pojo.TokenResp;

@Controller
@RequestMapping("/oauth")
public class OauthCtrl {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private static final String API_AUTHORIZE = "authorize";
    private static final String API_TOKEN = "token";
    private static final String API_VALIDATE = "validate";
    private static final String API_REVOKE = "revoke";

    private static final String P_RESPONSE_TYPE = "response_type";
    private static final String P_CLIENT_ID = "client_id";
    private static final String P_CLIENT_SECRET = "client_secret";
    private static final String P_REDIRECT_URI = "redirect_uri";
    private static final String P_PASSWORD = "password";
    private static final String P_USERNAME = "username";
    private static final String P_CODE = "code";
    private static final String P_STATE = "state";
    private static final String P_SCOPE = "scope";
    private static final String P_GRANT_TYPE = "grant_type";
    private static final String P_REFRESH_TOKEN = "refresh_token";
    private static final String P_ACCESS_TOKEN = "access_token";

    private static final String GRANT_TYPE_AUTHORIZATION_CODE = "authorization_code";
    private static final String GRANT_TYPE_PASSWORD = P_PASSWORD;
    private static final String GRANT_TYPE_CLIENT_CREDENTIALS = "client_credentials";
    private static final String GRANT_TYPE_DEVICE_TOKEN = "device_token";
    private static final String GRANT_TYPE_REFRESH_TOKEN = "refresh_token";

    private static final String RESPONSE_TYPE_CODE = P_CODE;
    private static final String RESPONSE_TYPE_TOKEN = API_TOKEN;
    private static final String RESPONSE_TYPE_DEVICE_CODE = "device_code";

    @RequestMapping(value = API_AUTHORIZE, method = {RequestMethod.GET})
    public void authorize(HttpServletRequest request, HttpServletResponse response) {

        String responseType = request.getParameter(P_RESPONSE_TYPE);
        if (RESPONSE_TYPE_CODE.equals(responseType)) {
            authorizeForCode(request, response);
        } else if (RESPONSE_TYPE_TOKEN.equals(responseType)) {
            authorizeForToken(request, response);
        } else if (RESPONSE_TYPE_DEVICE_CODE.equals(responseType)) {
            authorizeForDeviceCode(request, response);
        }

    }

    private void authorizeForCode(HttpServletRequest request, HttpServletResponse response) {
        String clientId = request.getParameter(P_CLIENT_ID);
        String redirectUri = request.getParameter(P_REDIRECT_URI);
        String scope = request.getParameter(P_SCOPE);
        String state = request.getParameter(P_STATE);

        if (StringUtils.isBlank(clientId)) {
            sendError(P_CLIENT_ID, response);
            return;
        }
        if (StringUtils.isBlank(redirectUri)) {
            sendError(P_REDIRECT_URI, response);
            return;
        }

        AuthResp resp = new AuthResp();
        resp.setCode("");
        resp.setState(state);
        sendRedirect(redirectUri, resp, response);

    }

    private void authorizeForToken(HttpServletRequest request, HttpServletResponse response) {
        String clientId = request.getParameter(P_CLIENT_ID);
        String redirectUri = request.getParameter(P_REDIRECT_URI);
        String scope = request.getParameter(P_SCOPE);
        String state = request.getParameter(P_STATE);

        if (StringUtils.isBlank(clientId)) {
            sendError(P_CLIENT_ID, response);
            return;
        }
        if (StringUtils.isBlank(redirectUri)) {
            sendError(P_REDIRECT_URI, response);
            return;
        }

        TokenResp resp = new TokenResp();
        resp.setAccessToken("");
        resp.setTokenType("");
        resp.setExpiresIn(0L);
        resp.setRefreshToken("");
        resp.setScope(scope);
        resp.setState(state);
        sendRedirect(redirectUri, resp, response);
    }

    private void authorizeForDeviceCode(HttpServletRequest request, HttpServletResponse response) {
        String clientId = request.getParameter(P_CLIENT_ID);
        String scope = request.getParameter(P_SCOPE);

        if (StringUtils.isBlank(clientId)) {
            sendError(P_CLIENT_ID, response);
            return;
        }

        Deviceresp resp = new Deviceresp();
        resp.setDeviceCode("");
        resp.setUserCode("");
        resp.setVerificationUrl("");
        resp.setQrcodeUrl("");
        resp.setExpiresIn(0L);
        resp.setInterval(0L);
        sendOk(resp, response);
    }

    @RequestMapping(value = API_TOKEN, method = {RequestMethod.GET})
    public void token(HttpServletRequest request, HttpServletResponse response) {

        String grantType = request.getParameter(P_GRANT_TYPE);
        if (GRANT_TYPE_AUTHORIZATION_CODE.equals(grantType)) {
            tokenByAuthorizationCode(request, response);
        } else if (GRANT_TYPE_PASSWORD.equals(grantType)) {
            tokenByPassword(request, response);
        } else if (GRANT_TYPE_CLIENT_CREDENTIALS.equals(grantType)) {
            tokenByClientCrendentials(request, response);
        } else if (GRANT_TYPE_DEVICE_TOKEN.equals(grantType)) {
            tokenByDeviceToken(request, response);
        } else if (GRANT_TYPE_REFRESH_TOKEN.equals(grantType)) {
            tokenByRefreshToken(request, response);
        }

    }

    private void tokenByAuthorizationCode(HttpServletRequest request, HttpServletResponse response) {
        String clientId = request.getParameter(P_CLIENT_ID);
        String clientSecret = request.getParameter(P_CLIENT_SECRET);
        String code = request.getParameter(P_CODE);
        String redirectUri = request.getParameter(P_REDIRECT_URI);
        String state = request.getParameter(P_STATE);

        if (StringUtils.isBlank(clientId)) {
            sendError(P_CLIENT_ID, response);
            return;
        }
        if (StringUtils.isBlank(clientSecret)) {
            sendError(P_CLIENT_SECRET, response);
            return;
        }
        if (StringUtils.isBlank(code)) {
            sendError(P_CODE, response);
            return;
        }

        TokenResp resp = new TokenResp();
        resp.setAccessToken("");
        resp.setTokenType("");
        resp.setExpiresIn(0L);
        resp.setRefreshToken("");
        resp.setScope("");
        resp.setState(state);
        sendOk(resp, response);
    }

    private void tokenByPassword(HttpServletRequest request, HttpServletResponse response) {
        String clientId = request.getParameter(P_CLIENT_ID);
        String clientSecret = request.getParameter(P_CLIENT_SECRET);
        String username = request.getParameter(P_USERNAME);
        String password = request.getParameter(P_PASSWORD);
        String scope = request.getParameter(P_SCOPE);

        if (StringUtils.isBlank(clientId)) {
            sendError(P_CLIENT_ID, response);
            return;
        }
        if (StringUtils.isBlank(clientSecret)) {
            sendError(P_CLIENT_SECRET, response);
            return;
        }
        if (StringUtils.isBlank(username)) {
            sendError(P_USERNAME, response);
            return;
        }
        if (StringUtils.isBlank(password)) {
            sendError(P_PASSWORD, response);
            return;
        }

        TokenResp resp = new TokenResp();
        resp.setAccessToken("");
        resp.setTokenType("");
        resp.setExpiresIn(0L);
        resp.setRefreshToken("");
        resp.setScope(scope);
        sendOk(resp, response);
    }

    private void tokenByClientCrendentials(HttpServletRequest request, HttpServletResponse response) {
        String clientId = request.getParameter(P_CLIENT_ID);
        String clientSecret = request.getParameter(P_CLIENT_SECRET);
        String scope = request.getParameter(P_SCOPE);

        if (StringUtils.isBlank(clientId)) {
            sendError(P_CLIENT_ID, response);
            return;
        }
        if (StringUtils.isBlank(clientSecret)) {
            sendError(P_CLIENT_SECRET, response);
            return;
        }

        TokenResp resp = new TokenResp();
        resp.setAccessToken("");
        resp.setTokenType("");
        resp.setExpiresIn(0L);
        resp.setRefreshToken("");
        resp.setScope(scope);
        sendOk(resp, response);
    }

    private void tokenByDeviceToken(HttpServletRequest request, HttpServletResponse response) {
        String clientId = request.getParameter(P_CLIENT_ID);
        String clientSecret = request.getParameter(P_CLIENT_SECRET);
        String code = request.getParameter(P_CODE);
        String scope = request.getParameter(P_SCOPE);

        if (StringUtils.isBlank(clientId)) {
            sendError(P_CLIENT_ID, response);
            return;
        }
        if (StringUtils.isBlank(clientSecret)) {
            sendError(P_CLIENT_SECRET, response);
            return;
        }
        if (StringUtils.isBlank(code)) {
            sendError(P_CODE, response);
            return;
        }

        TokenResp resp = new TokenResp();
        resp.setAccessToken("");
        resp.setTokenType("");
        resp.setExpiresIn(0L);
        resp.setRefreshToken("");
        resp.setScope(scope);
        sendOk(resp, response);
    }

    private void tokenByRefreshToken(HttpServletRequest request, HttpServletResponse response) {
        String clientId = request.getParameter(P_CLIENT_ID);
        String clientSecret = request.getParameter(P_CLIENT_SECRET);
        String refreshToken = request.getParameter(P_REFRESH_TOKEN);
        String scope = request.getParameter(P_SCOPE);

        if (StringUtils.isBlank(clientId)) {
            sendError(P_CLIENT_ID, response);
            return;
        }
        if (StringUtils.isBlank(clientSecret)) {
            sendError(P_CLIENT_SECRET, response);
            return;
        }
        if (StringUtils.isBlank(refreshToken)) {
            sendError(P_REFRESH_TOKEN, response);
            return;
        }

        TokenResp resp = new TokenResp();
        resp.setAccessToken("");
        resp.setTokenType("");
        resp.setExpiresIn(0L);
        resp.setRefreshToken("");
        resp.setScope(scope);
        sendOk(resp, response);
    }

    @RequestMapping(value = API_VALIDATE, method = {RequestMethod.GET})
    public void validate(HttpServletRequest request, HttpServletResponse response) {

        String accessToken = request.getParameter(P_ACCESS_TOKEN);
        if (StringUtils.isBlank(accessToken)) {
            sendError(P_ACCESS_TOKEN, response);
            return;
        }

        BaseResp resp = new BaseResp();
        sendOk(resp, response);
    }

    @RequestMapping(value = API_REVOKE, method = {RequestMethod.GET})
    public void revoke(HttpServletRequest request, HttpServletResponse response) {

        String accessToken = request.getParameter(P_ACCESS_TOKEN);
        if (StringUtils.isBlank(accessToken)) {
            sendError(P_ACCESS_TOKEN, response);
            return;
        }

        BaseResp resp = new BaseResp();
        sendOk(resp, response);
    }

    private void sendError(String error, HttpServletResponse response) {
        try {
            response.sendError(0);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    private void sendOk(Object resp, HttpServletResponse response) {
        try {
            ServletOutputStream os = response.getOutputStream();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
    
    private void sendRedirect(String redirectUri, Object resp, HttpServletResponse response){
        try {
            response.sendRedirect(redirectUri);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

}
