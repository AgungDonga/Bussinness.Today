package com.example.newsapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.newsapp.R;
import com.example.newsapp.adapter.NewsAdapter;
import com.example.newsapp.model.NewsResult;
import com.example.newsapp.viewmodel.NewsViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private NewsAdapter adapter;
    private RecyclerView recyclerView;
    private NewsViewModel newsViewModel;
    private static final String SOURCES = "google-news";
    private ArrayList<NewsResult> results = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.rv_piggy);

        newsViewModel = ViewModelProviders.of(this).get(NewsViewModel.class);
        newsViewModel.setNews();
        newsViewModel.getNews().observe(this, newsRequest -> {
            List<NewsResult> list = newsRequest.getResult();
            results.addAll(list);
            adapter.notifyDataSetChanged();
        });

        setupRecyclerview();
    }

    private void setupRecyclerview() {
        if (adapter == null) {
            adapter = new NewsAdapter(MainActivity.this, results);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(adapter);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setNestedScrollingEnabled(true);
        } else {
            adapter.notifyDataSetChanged();
        }

    }
}
