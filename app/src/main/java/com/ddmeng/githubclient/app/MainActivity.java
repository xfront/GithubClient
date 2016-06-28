package com.ddmeng.githubclient.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.ddmeng.githubclient.R;
import com.ddmeng.githubclient.model.Endpoints;
import com.ddmeng.githubclient.network.GitHubService;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.result)
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        result.setText("hello");
    }

    @OnClick(R.id.send)
    void testButtonClicked() {
        getAllEndpoints();
    }

    private void getAllEndpoints() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GitHubService gitHubService = retrofit.create(GitHubService.class);
        Call<Endpoints> endpoints = gitHubService.getEndpoints("");
        endpoints.enqueue(new Callback<Endpoints>() {
            @Override
            public void onResponse(Call<Endpoints> call, Response<Endpoints> response) {
                result.setText(response.body().toString());

            }

            @Override
            public void onFailure(Call<Endpoints> call, Throwable t) {
                result.setText(t.getMessage());

            }
        });
    }
}
