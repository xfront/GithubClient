package com.ddmeng.githubclient.app.home;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ddmeng.githubclient.R;
import com.ddmeng.githubclient.app.viewholders.RepoViewHolder;
import com.ddmeng.githubclient.data.models.Repo;

import java.util.List;

public class HomeListAdapter extends RecyclerView.Adapter<RepoViewHolder> {
    private List<Repo> repoList;

    public HomeListAdapter() {
    }

    public void setRepoList(List<Repo> repoList) {
        this.repoList = repoList;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public RepoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View repoViewHolderView = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.repo_viewholder_layout, parent, false);
        return new RepoViewHolder(repoViewHolderView);
    }

    @Override
    public void onBindViewHolder(RepoViewHolder holder, int position) {
        holder.populate(repoList.get(position));
    }

    @Override
    public int getItemCount() {
        return repoList != null ? repoList.size() : 0;
    }
}
