package com.bulain.oltu.util;

import org.apache.oltu.oauth2.common.OAuthProviderType;

public interface Constant {
    String PAGE_INDEX = "/oltu-demo/index.html";
    String PAGE_GET_CODE = "/oltu-demo/getCode.jsp";
    String PAGE_VALIDATE_CODE = "/oltu-demo/validateCode.jsp";
    String PAGE_SUCCESS = "/oltu-demo/success.html";

    String OAUTH_LOCATION_URI = "http://localhost:8082/oltu-demo/index.html";
    String OAUTH_REDIRECT_URI = "http://localhost:8082/oltu-demo/validateCode.jsp";

    OAuthProviderType OAUTH_PROVIDER_TYPE = OAuthProviderType.GITHUB;
    String OAUTH_CLIENT_ID = "23441d2e45a6470b6554";
    String OAUTH_SECRET = "61a6833d6cea39cdc6a7b7fb22c4b0e917374746";

    String P_ACCESS_TOKEN = "accessToken";
    String P_CODE = "code";

}
