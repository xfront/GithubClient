package com.ddmeng.githubclient.app.me;

import com.ddmeng.githubclient.app.BasePresenter;
import com.ddmeng.githubclient.app.BaseView;
import com.ddmeng.githubclient.data.models.Repo;

import java.util.List;

public interface MeContract {
    interface View extends BaseView<MeContract.Presenter> {

        void initViews();

        void showRepos(List<Repo> repos);
    }

    interface Presenter extends BasePresenter {
    }
}
