package com.example.suzuki_r_finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DailyChallenges extends AppCompatActivity {
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_challenges);
        username = getIntent().getStringExtra("USERNAME");
    }

    public void handleGoBack(View view) {
        finish();
    }

    public void startVocabMatching(View view) {
        Intent intent = new Intent(this, VocabMatchingGameActivity.class);
        intent.putExtra("USERNAME", username);
        startActivity(intent);
    }

    public void startIdentifyFood(View view) {
        Intent intent = new Intent(this, IdentifyFoodGameActivity.class);
        intent.putExtra("USERNAME", username);
        startActivity(intent);
    }

    public void startFamousPeople(View view) {
        Intent intent = new Intent(this, identifyFamousPeopleActivity.class);
        intent.putExtra("USERNAME", username);
        startActivity(intent);
    }
}