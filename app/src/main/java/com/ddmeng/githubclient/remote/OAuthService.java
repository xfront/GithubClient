package com.ddmeng.githubclient.remote;

import com.ddmeng.githubclient.data.models.AccessTokenResponse;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

public interface OAuthService {

    String BASE_URL = "https://github.com/login/oauth/";


    @FormUrlEncoded
    @POST("access_token")
    Observable<AccessTokenResponse> getAccessToken(@Field("client_id") String clientId,
                                                   @Field("client_secret") String clientSecret,
                                                   @Field("code") String code);


    @FormUrlEncoded
    @POST("access_token")
    Observable<AccessTokenResponse> getAccessToken(@Field("client_id") String clientId,
                                                   @Field("client_secret") String clientSecret,
                                                   @Field("code") String code,
                                                   @Field("redirect_uri") String redirectUri,
                                                   @Field("state") String state);
}
