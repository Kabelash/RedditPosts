package com.kabelash.redditposts.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final String BASE_URL="https://www.reddit.com";

    private static RetrofitClient myClient;
    private Retrofit retrofit;
    private RetrofitClient(){
        retrofit=new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
    }
    public static synchronized RetrofitClient getInstance(){
        if (myClient==null){
            myClient=new RetrofitClient();
        }
        return myClient;
    }
    public RedditApi getMyApi(){
        return retrofit.create(RedditApi.class);
    }
}