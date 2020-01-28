package com.kabelash.redditposts.model.children;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {
    @SerializedName("title")
    @Expose
    public String title;

    @SerializedName("id")
    @Expose
    public String rpid;

    @SerializedName("author")
    @Expose
    public String author;

    @SerializedName("permalink")
    @Expose
    public String permalink;

    @SerializedName("body")
    @Expose
    private String body;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public String getRpid() {
        return rpid;
    }

    public String getAuthor() {
        return author;
    }

    public String getPermalink() {
        return permalink;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setRpid(String rpid) {
        this.rpid = rpid;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPermalink(String permalink) {
        this.permalink = permalink;
    }
}
