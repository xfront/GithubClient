package com.ddmeng.githubclient.network;

import com.ddmeng.githubclient.model.Endpoints;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;


public interface GitHubService {

    @GET
    Call<Endpoints> getEndpoints(@Url String url);
}
