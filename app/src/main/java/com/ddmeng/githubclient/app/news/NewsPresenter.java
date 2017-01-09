package com.ddmeng.githubclient.app.news;

import com.ddmeng.githubclient.account.AccountUtil;
import com.ddmeng.githubclient.data.models.Event;
import com.ddmeng.githubclient.data.models.User;
import com.ddmeng.githubclient.remote.GitHubService;
import com.ddmeng.githubclient.remote.ServiceGenerator;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class NewsPresenter implements NewsContract.Presenter {
    private NewsContract.View view;
    private AccountUtil accountUtil;

    public NewsPresenter(NewsContract.View view, AccountUtil accountUtil) {
        this.view = view;
        this.accountUtil = accountUtil;
        this.view.setPresenter(this);
    }

    @Override
    public void start() {
        view.initViews();
        if (accountUtil.isSignedIn()) {
            getReceivedEvents(accountUtil.getCurrentUser());
        }
    }

    private void getReceivedEvents(User user) {
        GitHubService service = ServiceGenerator.createService(GitHubService.class);
        service.getReceivedEvents(user.getLogin())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Event>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<Event> events) {
                        view.showReceivedEvents(events);
                    }
                });
    }
}
