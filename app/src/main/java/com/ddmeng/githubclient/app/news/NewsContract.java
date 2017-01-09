package com.ddmeng.githubclient.app.news;

import com.ddmeng.githubclient.app.BasePresenter;
import com.ddmeng.githubclient.app.BaseView;
import com.ddmeng.githubclient.data.models.Event;

import java.util.List;

public interface NewsContract {
    interface View extends BaseView<NewsContract.Presenter> {

        void initViews();

        void showReceivedEvents(List<Event> events);
    }

    interface Presenter extends BasePresenter {
    }
}
