package com.kabelash.redditposts.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kabelash.redditposts.R;
import com.kabelash.redditposts.model.children.Children;
import com.kabelash.redditposts.ui.adapter.CommentsAdapter;
import com.kabelash.redditposts.viewmodel.PostsListViewModel;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class CommentsActivity  extends AppCompatActivity {
    private RecyclerView recyclerview;
    private PostsListViewModel postListViewModel;
    private CommentsAdapter commentsAdapter;
    ProgressBar pBar;
    String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String commentLink = getIntent().getStringExtra("COMMENT_LINK");
        Toast.makeText(this, commentLink, Toast.LENGTH_LONG).show();

        recyclerview = findViewById(R.id.recyclerView);
        postListViewModel = ViewModelProviders.of(this).get(PostsListViewModel.class);
        pBar = findViewById(R.id.progressBar);

        postListViewModel.getComments(commentLink+ ".json").observe(CommentsActivity.this, new Observer<ArrayList<Children>>() {
            @Override
            public void onChanged(ArrayList<Children> data) {
                // handle changes here, to use the data write code below
                commentsAdapter = new CommentsAdapter(data, CommentsActivity.this);
                recyclerview.setLayoutManager(new LinearLayoutManager(CommentsActivity.this));
                recyclerview.setItemAnimator(new DefaultItemAnimator());
                recyclerview.setAdapter(commentsAdapter);

                //Hiding progress bar
                pBar.setVisibility(View.GONE);

            }
        });

    }
}
