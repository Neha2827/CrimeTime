package com.project.crimetime;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("/v2/top-headlines")
    Call<Result> getNews(@Query("sources") String sourceValue,@Query("apiKey") String apiKey);
}
