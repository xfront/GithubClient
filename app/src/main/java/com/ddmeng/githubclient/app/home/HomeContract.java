package com.ddmeng.githubclient.app.home;

import com.ddmeng.githubclient.app.BasePresenter;
import com.ddmeng.githubclient.app.BaseView;
import com.ddmeng.githubclient.data.models.User;

public interface HomeContract {

    interface View extends BaseView<Presenter> {
        void initDrawerMenu();

        void initActionBar();

        void initMainList();

        void showUserInformation(User currentUser);

        void showSignInButton();

    }

    interface Presenter extends BasePresenter {
        void updateSignInState();
    }
}
