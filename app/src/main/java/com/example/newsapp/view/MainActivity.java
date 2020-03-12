package com.example.newsapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;

import com.example.newsapp.R;
import com.example.newsapp.adapter.NewsAdapter;
import com.example.newsapp.model.NewsResult;
import com.example.newsapp.viewmodel.NewsViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private int progressStatus = 0;
    private NewsAdapter adapter;
    private RecyclerView recyclerView;
    private static final String COUNTRY = "id";
    private static final String CATEGORY = "business";
    private ArrayList<NewsResult> results = new ArrayList<>();
    private Handler handler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.rv_piggy);
        progressBar = findViewById(R.id.progressbar);



        new Thread(() -> {
            while (progressStatus < 100) {
                progressStatus += 1;
                // Update the progress bar and display the
                //current value in the text view
                handler.post(() -> progressBar.setProgress(progressStatus));
                try {
                    // Sleep for 200 milliseconds.
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }).start();

        NewsViewModel newsViewModel = ViewModelProviders.of(this).get(NewsViewModel.class);
        newsViewModel.setNews(COUNTRY,CATEGORY);
        newsViewModel.getNews().observe(this, newsRequest -> {
            List<NewsResult> list = newsRequest.getResult();
            results.addAll(list);
            adapter.notifyDataSetChanged();
            progressBar.setVisibility(View.GONE);
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
