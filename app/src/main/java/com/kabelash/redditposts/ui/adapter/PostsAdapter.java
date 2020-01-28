package com.kabelash.redditposts.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.kabelash.redditposts.R;
import com.kabelash.redditposts.databinding.PostsListBinding;
import com.kabelash.redditposts.model.children.Children;
import com.kabelash.redditposts.ui.CommentsActivity;

import java.util.ArrayList;

public class PostsAdapter  extends RecyclerView.Adapter<PostsAdapter.ViewHolder>{
    private static final String URL="https://www.reddit.com";
    private ArrayList<Children> arrayList;
    private Context context;
    private LayoutInflater layoutInflater;

    public PostsAdapter(ArrayList<Children> arrayList, Context context) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        PostsListBinding postsListBinding = DataBindingUtil.inflate(layoutInflater, R.layout.posts_list, parent, false);
        return new ViewHolder(postsListBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull PostsAdapter.ViewHolder holder, int position) {
        final Children postsListViewModel = arrayList.get(position);
        holder.bind(postsListViewModel);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //tapping a row item opens the link from the post (use intents to open using preferred browser)
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(URL + postsListViewModel.getData().permalink));
                Intent chooser = Intent.createChooser(intent, "Open with");
                context.startActivity(chooser);
            }
        });

        //Open comments activity on button click
        Button bComments = holder.itemView.findViewById(R.id.btnComments);

        bComments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context.getApplicationContext(), CommentsActivity.class);
                intent.putExtra("COMMENT_LINK", postsListViewModel.getData().permalink);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private PostsListBinding postsListBinding;

        public ViewHolder(@NonNull PostsListBinding postsListBinding) {
            super(postsListBinding.getRoot());
            this.postsListBinding = postsListBinding;
        }

        public void bind(Children myli) {
            this.postsListBinding.setPostlist(myli.getData());
            postsListBinding.executePendingBindings();
        }

        public PostsListBinding getPostsListBinding() {
            return postsListBinding;
        }


    }
}
