package com.tomek.ujebse;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by tomek on 03.08.15.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{
    private List<Links> linksList = Links.listAll(Links.class);

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Links link = linksList.get(position);
        holder.bind(link);
    }

    @Override
    public int getItemCount() {
        return linksList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView originalLink;
        private TextView shortcutLink;

        public ViewHolder(View itemView) {
            super(itemView);
            originalLink = (TextView) itemView.findViewById(R.id.originalLink);
            shortcutLink = (TextView) itemView.findViewById(R.id.shortcutLink);
        }
        public void bind(Links links) {
            originalLink.setText(links.original);
            shortcutLink.setText(links.shortcut);
        }

    }
}
