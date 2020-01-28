package com.kabelash.redditposts.network;

import com.kabelash.redditposts.model.RedditPosts;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface RedditApi {

    //To get posts
    @GET("/.json")
    Call<RedditPosts> getPosts();

    //To get comments
    @GET
    Call<List<RedditPosts>> getComments(@Url String post_name);
}
