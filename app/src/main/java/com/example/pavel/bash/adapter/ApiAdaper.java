package com.example.pavel.bash.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pavel.bash.R;
import com.example.pavel.bash.model.Post;

import java.util.List;

/**
 * Created by pavel on 08.01.18.
 */

public class ApiAdaper extends RecyclerView.Adapter<ApiAdaper.customViewHolder> {

    List<Post> posts;

    public ApiAdaper(List<Post> posts) {
        this.posts = posts;
    }

    @Override
    public customViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.post_item, null);

        return new customViewHolder;
    }

    @Override
    public void onBindViewHolder(customViewHolder holder, int position) {
        holder.PostText.setText(posts.get(position).getElementPureHtml());
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public class customViewHolder extends RecyclerView.ViewHolder{

        TextView PostText;

        public customViewHolder(View itemView) {
            super(itemView);
            PostText =  (TextView)itemView.findViewById(R.id.postTV);
        }
    }
}