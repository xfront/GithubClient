package com.ddmeng.githubclient.remote;

import com.ddmeng.githubclient.data.models.Endpoints;
import com.ddmeng.githubclient.data.models.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;
import rx.Observable;


public interface GitHubService {

    @GET
    Call<Endpoints> getAllEndpoints(@Url String url);

    @GET("user")
    Observable<User> getCurrentUser();
}
