package com.ddmeng.githubclient.network;

import okhttp3.OkHttpClient;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

public class ServiceGenerator {
    public static final String API_BASE_URL = "https://api.github.com";

    private static OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();

    private static Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());

    public static <S> S createService(Class<S> serviceClass) {
        OkHttpClient client = httpClientBuilder.build();
        Retrofit retrofit = retrofitBuilder.client(client).build();
        return retrofit.create(serviceClass);
    }

}
