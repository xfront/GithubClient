package com.ddmeng.githubclient.network;

import com.ddmeng.githubclient.models.AccessTokenResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface OAuthService {

    String BASE_URL = "https://github.com/login/oauth/";


    @FormUrlEncoded
    @POST("access_token")
    Call<AccessTokenResponse> getAccessToken(@Field("client_id") String clientId, @Field("client_secret") String clientSecret,
                                             @Field("code") String code);


    @FormUrlEncoded
    @POST("access_token")
    Call<AccessTokenResponse> getAccessToken(@Field("client_id") String clientId, @Field("client_secret") String clientSecret,
                                             @Field("code") String code, @Field("redirect_uri") String redirectUri, @Field("state")
                                             String state);
}
