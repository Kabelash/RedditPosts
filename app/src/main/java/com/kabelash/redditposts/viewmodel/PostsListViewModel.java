package com.kabelash.redditposts.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.kabelash.redditposts.model.RedditPosts;
import com.kabelash.redditposts.model.children.Children;
import com.kabelash.redditposts.network.RedditApi;
import com.kabelash.redditposts.network.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class PostsListViewModel extends ViewModel {
    public MutableLiveData<ArrayList<Children>> mutableLiveData = new MutableLiveData<>();

    public PostsListViewModel() {

    }

    //Get all posts
    public MutableLiveData<ArrayList<Children>> getPosts() {

        final MutableLiveData<ArrayList<Children>> localData = new MutableLiveData<>();

        RedditApi api = RetrofitClient.getInstance().getMyApi();
        Call<RedditPosts> call = api.getPosts();
        call.enqueue(new Callback<RedditPosts>() {
            @Override
            public void onResponse(Call<RedditPosts> call, Response<RedditPosts> response) {
                ArrayList<Children> childrenList = response.body().getData().getChildren();

                for( int i = 0; i<childrenList.size(); i++) {
                    mutableLiveData.setValue(childrenList);
                    localData.setValue(childrenList);
                }
            }

            @Override
            public void onFailure(Call<RedditPosts> call, Throwable t) {
                Log.e(TAG, "onFailure: Unable to retrieve JSON: " + t.getMessage() );

            }
        });

        return localData;
    }

    //Get all comments for selected posts
    public MutableLiveData<ArrayList<Children>> getComments(String post_name) {

        final MutableLiveData<ArrayList<Children>> commentsData = new MutableLiveData<>();

        RedditApi api = RetrofitClient.getInstance().getMyApi();
        Call<List<RedditPosts>> call = api.getComments(post_name);
        call.enqueue(new Callback<List<RedditPosts>>() {
            @Override
            public void onResponse(Call<List<RedditPosts>> call, Response<List<RedditPosts>> response) {
                ArrayList<Children> childrenList = response.body().get(1).getData().getChildren();

                for( int i = 0; i<childrenList.size(); i++) {
                    mutableLiveData.setValue(childrenList);
                    commentsData.setValue(childrenList);
                }
                //Log.e(TAG, "onSuccess: JSON: " + response.body().get(1).getData().getChildren() );
            }

            @Override
            public void onFailure(Call<List<RedditPosts>> call, Throwable t) {
                Log.e(TAG, "onFailure: Unable to retrieve JSON: " + t.getMessage() );

            }
        });

        return commentsData;
    }
}