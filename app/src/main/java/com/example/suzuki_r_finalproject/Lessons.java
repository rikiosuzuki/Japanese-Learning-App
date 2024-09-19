package com.example.suzuki_r_finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Lessons extends AppCompatActivity {
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lessons);
        username = getIntent().getStringExtra("USERNAME");
    }

    public void handleBasicHiragana(View view) {
        Intent intent  = new Intent(this, BasicHiragana.class);
        intent.putExtra("USERNAME", username);
        startActivity(intent);
    }

    public void handleBasicKatakana(View view) {
        Intent intent = new Intent(this, BasicKatakana.class);
        intent.putExtra("USERNAME", username);
        startActivity(intent);
    }

    public void handleJapanesePronunciation(View view) {
        Intent intent = new Intent(this, JapanesePronunciationActivity.class);
        intent.putExtra("USERNAME", username);
        startActivity(intent);
    }

    public void handleHistoryOfLanguage(View view) {
        Intent intent = new Intent(this, HistoryActivity.class);
        intent.putExtra("USERNAME", username);
        startActivity(intent);
    }

    public void handleVocabulary1(View view) {
        Intent intent = new Intent(this, Vocabulary1Activity.class);
        intent.putExtra("USERNAME", username);
        startActivity(intent);
    }

    public void handleVocabulary2(View view) {
        Intent intent = new Intent(this, Vocabulary2Activity.class);
        intent.putExtra("USERNAME", username);
        startActivity(intent);
    }

    public void handleVocabulary3(View view) {
        Intent intent = new Intent(this, Vocabulary3Activity.class);
        intent.putExtra("USERNAME", username);
        startActivity(intent);
    }

    public void handleTakeVocabTest(View view) {
        Intent intent = new Intent(this, VocabularyTestActivity.class);
        intent.putExtra("USERNAME", username);
        startActivity(intent);
    }

    public void handleGrammar1(View view) {
        Intent intent = new Intent(this, Grammar1Activity.class);
        intent.putExtra("USERNAME", username);
        startActivity(intent);
    }

    public void handleGrammar2(View view) {
        Intent intent = new Intent(this, Grammar2Activity.class);
        intent.putExtra("USERNAME", username);
        startActivity(intent);
    }

    public void handleWeather(View view) {
        Intent intent = new Intent(this, WeatherActivity.class);
        intent.putExtra("USERNAME", username);
        startActivity(intent);
    }

    public void handleGeography(View view) {
        Intent intent = new Intent(this, GeographyActivity.class);
        intent.putExtra("USERNAME", username);
        startActivity(intent);
    }

    public void handleGoBack(View view) {
        finish();
    }
}