package com.ddmeng.githubclient.network;

import com.ddmeng.githubclient.models.Endpoints;
import com.ddmeng.githubclient.models.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;


public interface GitHubService {

    @GET
    Call<Endpoints> getAllEndpoints(@Url String url);

    @GET("user")
    Call<User> getCurrentUser();
}
