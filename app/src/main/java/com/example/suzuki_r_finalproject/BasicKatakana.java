package com.example.suzuki_r_finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class BasicKatakana extends AppCompatActivity {

    private Button takeQuiz, goBack;

    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_katakana);
        takeQuiz = findViewById(R.id.takeQuizKatakana);
        goBack = findViewById(R.id.goBackButton3);
        username = getIntent().getStringExtra("USERNAME");
        goBack.setOnClickListener(view ->{
            finish();
        });
        takeQuiz.setOnClickListener(view ->{
            Intent intent = new Intent(this, BasicKatakanaQuizActivity.class);
            intent.putExtra("USERNAME", username);
            startActivity(intent);

        });
    }
}