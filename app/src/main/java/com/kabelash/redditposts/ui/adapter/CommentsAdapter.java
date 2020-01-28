package com.kabelash.redditposts.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.kabelash.redditposts.R;
import com.kabelash.redditposts.databinding.CommentsListBinding;
import com.kabelash.redditposts.model.children.Children;

import java.util.ArrayList;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.ViewHolder>{
    private ArrayList<Children> arrayList;
    private Context context;
    private LayoutInflater layoutInflater;

    public CommentsAdapter(ArrayList<Children> arrayList, Context context) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        CommentsListBinding commentsListBinding = DataBindingUtil.inflate(layoutInflater, R.layout.comments_list, parent, false);
        return new CommentsAdapter.ViewHolder(commentsListBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentsAdapter.ViewHolder holder, int position) {
        final Children postsListViewModel = arrayList.get(position);
        holder.bind(postsListViewModel);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private CommentsListBinding commentsListBinding;

        public ViewHolder(@NonNull CommentsListBinding commentsListBinding) {
            super(commentsListBinding.getRoot());
            this.commentsListBinding = commentsListBinding;
        }

        public void bind(Children myli) {
            this.commentsListBinding.setCommentslist(myli.getData());
            commentsListBinding.executePendingBindings();
        }

        public CommentsListBinding getcommentsListBinding() {
            return commentsListBinding;
        }


    }
}
