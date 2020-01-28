package com.kabelash.redditposts.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kabelash.redditposts.R;
import com.kabelash.redditposts.model.children.Children;
import com.kabelash.redditposts.ui.adapter.PostsAdapter;
import com.kabelash.redditposts.viewmodel.PostsListViewModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerview;
    private PostsListViewModel postListViewModel;
    private PostsAdapter adapter;
    ProgressBar pBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerview = findViewById(R.id.recyclerView);
        postListViewModel = ViewModelProviders.of(this).get(PostsListViewModel.class);
        pBar = findViewById(R.id.progressBar);

        updateUi();
    }

    private void updateUi() {
        postListViewModel.getPosts().observe(MainActivity.this, new Observer<ArrayList<Children>>() {
            @Override
            public void onChanged(ArrayList<Children> data) {
                // handle changes here, to use the data write code below
                adapter = new PostsAdapter(data, MainActivity.this);
                recyclerview.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                recyclerview.setItemAnimator(new DefaultItemAnimator());
                recyclerview.setAdapter(adapter);

                //Hiding progress bar
                pBar.setVisibility(View.GONE);

            }
        });
    }
}
