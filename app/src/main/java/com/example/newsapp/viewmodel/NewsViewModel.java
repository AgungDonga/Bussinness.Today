package com.example.newsapp.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.newsapp.BuildConfig;
import com.example.newsapp.apihelper.ApiService;
import com.example.newsapp.apihelper.UtilsApi;
import com.example.newsapp.model.NewsRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsViewModel extends ViewModel {

    private static final String API_TOKEN = BuildConfig.API_KEY;
//    private static final String SOURCES = "google-news";
    private MutableLiveData<NewsRequest> liveDataNews = new MutableLiveData<>();


    public void setNews(String country, String category) {

        ApiService mApiService = UtilsApi.getApiService();
        Call<NewsRequest> call = mApiService.getNewsList(country, category, API_TOKEN);
        call.enqueue(new Callback<NewsRequest>() {

            public void onResponse(Call<NewsRequest> call, Response<NewsRequest> response) {
                Log.d("onResponse", response.body().getStatus());
                liveDataNews.setValue(response.body());

            }

            public void onFailure(Call<NewsRequest> call, Throwable t) {
                Log.e("onFailure", t.getMessage());
            }
        });
    }

    public LiveData<NewsRequest> getNews() {
        return liveDataNews;
    }
}
