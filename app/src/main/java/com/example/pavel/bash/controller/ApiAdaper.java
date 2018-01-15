package com.example.pavel.bash.controller;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pavel.bash.R;
import com.example.pavel.bash.model.DataController;
import com.example.pavel.bash.model.Post;

import java.util.List;



public class ApiAdaper extends RecyclerView.Adapter<ApiAdaper.customViewHolder> {

    List<Post> posts;
    DataController dataController;

    public ApiAdaper(List<Post> posts, DataController dataController) {
        this.posts = posts;
        this.dataController = dataController;

    }

    @Override
    public customViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.post_item, null);

        return new customViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(customViewHolder holder, int position) {
        holder.PostText.setText(dataController.returnClear(posts.get(position).getElementPureHtml()));

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
