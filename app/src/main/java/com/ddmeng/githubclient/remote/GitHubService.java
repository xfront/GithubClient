package com.ddmeng.githubclient.remote;

import com.ddmeng.githubclient.data.models.Endpoints;
import com.ddmeng.githubclient.data.models.Repo;
import com.ddmeng.githubclient.data.models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;
import rx.Observable;


public interface GitHubService {

    @GET
    Call<Endpoints> getAllEndpoints(@Url String url);

    @GET("user")
    Observable<User> getCurrentUser();

    @GET("users/{user}/repos")
    Observable<List<Repo>> getUserReposObservable(@Path("user") String user);

    @GET("users/{user}/following")
    Observable<List<User>> getUserFollowingObservable(@Path("user") String user);
}
