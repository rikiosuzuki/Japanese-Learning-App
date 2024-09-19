package com.example.suzuki_r_finalproject;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Vocabulary1Activity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocabulary1);

        // You can retrieve the username if needed
        String username = getIntent().getStringExtra("USERNAME");

        // Initialize any views or logic here
    }

    public void handleGoBack(View view) {
        finish();
    }
}
