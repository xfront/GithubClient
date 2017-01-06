package com.ddmeng.githubclient.app.me;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ddmeng.githubclient.R;
import com.ddmeng.githubclient.account.AccountUtil;
import com.ddmeng.githubclient.app.GitHubApplication;
import com.ddmeng.githubclient.app.home.HomeListAdapter;
import com.ddmeng.githubclient.data.models.Repo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MeFragment extends Fragment implements MeContract.View {
    @BindView(R.id.main_content_list)
    RecyclerView mainContentList;

    private AccountUtil accountUtil;
    private MeContract.Presenter presenter;
    private HomeListAdapter mainListAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_me, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        accountUtil = ((GitHubApplication) getActivity().getApplication()).getComponent().getAccountUtil();
        new MePresenter(this, accountUtil);
        presenter.start();
    }


    @Override
    public void setPresenter(MeContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void initViews() {
        mainContentList.setLayoutManager(new LinearLayoutManager(getContext()));
        mainListAdapter = new HomeListAdapter();
        mainContentList.setAdapter(mainListAdapter);
    }

    @Override
    public void showRepos(List<Repo> repos) {
        mainListAdapter.setRepoList(repos);
        mainListAdapter.notifyDataSetChanged();
    }
}
