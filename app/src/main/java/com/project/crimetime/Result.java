package com.project.crimetime;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Result {

    public String status;
    public int totalResults;

    @SerializedName("articles")
    public ArrayList<Article> articleList;
}
