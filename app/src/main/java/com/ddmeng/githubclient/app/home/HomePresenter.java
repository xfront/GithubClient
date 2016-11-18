package com.ddmeng.githubclient.app.home;

import com.ddmeng.githubclient.account.AccountUtil;

public class HomePresenter implements HomeContract.Presenter {
    private HomeContract.View view;
    private AccountUtil accountUtil;

    public HomePresenter(HomeContract.View view, AccountUtil accountUtil) {
        this.view = view;
        this.accountUtil = accountUtil;
        view.setPresenter(this);
    }

    @Override
    public void start() {
        view.initActionBar();
        view.initDrawerMenu();
    }

    @Override
    public void updateSignInState() {
        if (accountUtil.isSignedIn()) {
            view.showUserInformation(accountUtil.getCurrentUser());
        } else {
            view.showSignInButton();
        }
    }
}
