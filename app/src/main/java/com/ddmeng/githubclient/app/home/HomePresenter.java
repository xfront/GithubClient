package com.ddmeng.githubclient.app.home;

import com.ddmeng.githubclient.account.AccountUtil;
import com.ddmeng.githubclient.data.models.Repo;
import com.ddmeng.githubclient.data.models.User;
import com.ddmeng.githubclient.remote.GitHubService;
import com.ddmeng.githubclient.remote.ServiceGenerator;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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
        view.initMainList();
    }

    @Override
    public void updateSignInState() {
        if (accountUtil.isSignedIn()) {
            User currentUser = accountUtil.getCurrentUser();
            view.showUserInformation(currentUser);
            getRepos(currentUser);
        } else {
            view.showSignInButton();
        }
    }

    private void getRepos(User user) {
        GitHubService service = ServiceGenerator.createService(GitHubService.class);
        service.getUserReposObservable(user.getLogin())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Repo>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<Repo> repos) {
                        view.showRepos(repos);

                    }
                });
    }
}
