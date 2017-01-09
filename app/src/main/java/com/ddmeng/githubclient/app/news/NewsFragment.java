package com.ddmeng.githubclient.app.news;

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
import com.ddmeng.githubclient.data.models.Event;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsFragment extends Fragment implements NewsContract.View {

    @BindView(R.id.news_recycler_view)
    RecyclerView newsRecyclerView;

    private NewsContract.Presenter presenter;
    private EventListAdapter eventListAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.news_fragment_layout, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        AccountUtil accountUtil = ((GitHubApplication) getActivity().getApplication()).getComponent().getAccountUtil();
        new NewsPresenter(this, accountUtil);
        presenter.start();
    }

    @Override
    public void initViews() {
        eventListAdapter = new EventListAdapter();
        newsRecyclerView.setAdapter(eventListAdapter);
        newsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

    }

    @Override
    public void showReceivedEvents(List<Event> events) {
        eventListAdapter.setEvents(events);
        eventListAdapter.notifyDataSetChanged();
    }

    @Override
    public void setPresenter(NewsContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
