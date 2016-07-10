package com.ddmeng.githubclient.network;

import retrofit2.http.Field;
import retrofit2.http.POST;

public interface OAuthService {

    String BASE_URL = "https://github.com/login/oauth/";

    @POST("access_token")
    void getAccessToken(@Field("client_id") String clientId, @Field("client_secret") String clientSecret,
                        @Field("code") String code, @Field("redirect_uri") String redirectUri, @Field("state")
                        String state);
}
