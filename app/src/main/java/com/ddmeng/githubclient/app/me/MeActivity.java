package com.ddmeng.githubclient.app.me;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.ddmeng.githubclient.R;
import com.ddmeng.githubclient.account.AccountUtil;
import com.ddmeng.githubclient.app.GitHubApplication;
import com.ddmeng.githubclient.app.home.HomeListAdapter;
import com.ddmeng.githubclient.data.models.Repo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MeActivity extends AppCompatActivity implements MeContract.View {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.main_content_list)
    RecyclerView mainContentList;

    private AccountUtil accountUtil;
    private MeContract.Presenter presenter;
    private HomeListAdapter mainListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me);
        ButterKnife.bind(this);
        accountUtil = ((GitHubApplication) getApplication()).getComponent().getAccountUtil();
        new MePresenter(this, accountUtil);
        presenter.start();

    }

    @Override
    public void setPresenter(MeContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void initViews() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mainContentList.setLayoutManager(new LinearLayoutManager(this));
        mainListAdapter = new HomeListAdapter();
        mainContentList.setAdapter(mainListAdapter);
    }

    @Override
    public void showRepos(List<Repo> repos) {
        mainListAdapter.setRepoList(repos);
        mainListAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
