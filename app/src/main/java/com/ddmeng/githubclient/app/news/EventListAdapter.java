package com.ddmeng.githubclient.app.news;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ddmeng.githubclient.R;
import com.ddmeng.githubclient.app.viewholders.EventViewHolder;
import com.ddmeng.githubclient.data.models.Event;

import java.util.List;

public class EventListAdapter extends RecyclerView.Adapter<EventViewHolder> {
    private List<Event> events;

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    @Override
    public EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_viewholder_layout, parent, false);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EventViewHolder holder, int position) {
        holder.populate(events.get(position));
    }

    @Override
    public int getItemCount() {
        return events != null ? events.size() : 0;
    }
}
