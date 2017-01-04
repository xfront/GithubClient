package com.ddmeng.githubclient.app.home;

import com.ddmeng.githubclient.app.BasePresenter;
import com.ddmeng.githubclient.app.BaseView;
import com.ddmeng.githubclient.data.models.Repo;
import com.ddmeng.githubclient.data.models.User;

import java.util.List;

public interface HomeContract {

    interface View extends BaseView<Presenter> {
        void initDrawerMenu();

        void initActionBar();

        void initMainList();

        void showUserInformation(User currentUser);

        void showSignInButton();

        void showRepos(List<Repo> repos);
    }

    interface Presenter extends BasePresenter {
        void updateSignInState();
    }
}
