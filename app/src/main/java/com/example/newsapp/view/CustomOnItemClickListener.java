package com.example.newsapp.view;

import android.view.View;

public class CustomOnItemClickListener implements View.OnClickListener {
    private int position;
    private OnItemClickCallback onItemClickCallback;

    public CustomOnItemClickListener(int position, OnItemClickCallback onItemClickCallback) {
        this.position = position;
        this.onItemClickCallback = onItemClickCallback;
    }

    @Override

    public void onClick(View v) {
        onItemClickCallback.onItemClickCallback(v,position);

    }

    public interface OnItemClickCallback {
        void onItemClickCallback(View view, int position);
    }

}
