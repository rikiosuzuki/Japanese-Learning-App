package com.example.suzuki_r_finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class BasicHiragana extends AppCompatActivity {
    String username;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_hiragana);
        button = findViewById(R.id.goBackButton2);
        button.setOnClickListener(view ->{
            finish();
        });
        username = getIntent().getStringExtra("USERNAME");
    }

    public void handleTakeQuiz(View view) {
        Intent intent = new Intent(this, BasicHiraganaQuizActivity.class);
        intent.putExtra("USERNAME", username);
        startActivity(intent);
    }
}