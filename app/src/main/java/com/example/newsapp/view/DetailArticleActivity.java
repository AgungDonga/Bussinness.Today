package com.example.newsapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.util.Log;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.newsapp.R;
import com.example.newsapp.model.NewsResult;

public class DetailArticleActivity extends AppCompatActivity {

    ImageView ivImage;

    TextView tvTitle, tvDate, tvContent;

    public static final String EXTRA_ARTICLE = "extra_article";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_article);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initContent();

        NewsResult result = getIntent().getParcelableExtra(EXTRA_ARTICLE);


        if (result != null){
            String image = result.getImage();
            String title = result.getTitle();
            String date = result.getDate();
            String content = result.getContent();


            Glide.with(this).load(image).into(ivImage);

            tvTitle.setText(title);
            tvDate.setText(date);
            tvContent.setText(content);
        }
    }


    private void initContent() {
        ivImage = findViewById(R.id.img_view);
        tvTitle = findViewById(R.id.tv_title);
        tvDate = findViewById(R.id.tv_realease_date);

        tvContent = findViewById(R.id.tv_content);
    }
}
