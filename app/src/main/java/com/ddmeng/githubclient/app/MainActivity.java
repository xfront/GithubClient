package com.ddmeng.githubclient.app;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.ddmeng.githubclient.R;
import com.ddmeng.githubclient.model.Endpoints;
import com.ddmeng.githubclient.network.GitHubService;
import com.ddmeng.githubclient.network.ServiceGenerator;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.navigation_view)
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                item.setChecked(true);
                drawerLayout.closeDrawers();
                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    void testButtonClicked() {
        getAllEndpoints();
    }

    private void getAllEndpoints() {

        GitHubService gitHubService = ServiceGenerator.createService(GitHubService.class);
        Call<Endpoints> endpoints = gitHubService.getEndpoints("");
        endpoints.enqueue(new Callback<Endpoints>() {
            @Override
            public void onResponse(Call<Endpoints> call, Response<Endpoints> response) {

            }

            @Override
            public void onFailure(Call<Endpoints> call, Throwable t) {

            }
        });
    }
}
