package com.example.newsapp.apihelper;

import com.example.newsapp.model.NewsRequest;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("top-headlines")
    Call<NewsRequest> getNewsList(@Query("country") String country,
                                  @Query("category") String category,
                                  @Query("apiKey") String apiKey);
}
