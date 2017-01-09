package com.ddmeng.githubclient.app.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ddmeng.githubclient.R;
import com.ddmeng.githubclient.data.models.Event;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EventViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.actor)
    TextView actorText;
    @BindView(R.id.action)
    TextView actionText;
    @BindView(R.id.repo)
    TextView repoText;
    @BindView(R.id.time)
    TextView timeText;

    public EventViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void populate(Event event) {
        actorText.setText(event.getActor().getLogin());
        actionText.setText(event.getPayload().getAction());
        repoText.setText(event.getRepo().getName());
        timeText.setText(event.getCreatedAt());
    }
}
