package com.example.suzuki_r_finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Dashboard extends AppCompatActivity {
    TextView welcome, userLevelTextView;
    String username;
    Button lessons, dailyChallenges, settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        welcome = findViewById(R.id.welcomeUser);
        userLevelTextView = findViewById(R.id.tvUserLevel);  // Reference to the level TextView
        username = getIntent().getStringExtra("USERNAME");
        welcome.setText("Welcome " + username + "!");
        lessons = findViewById(R.id.btnLessons);
        dailyChallenges = findViewById(R.id.btnChallenges);
        settings = findViewById(R.id.btnSettings);

        lessons.setOnClickListener(view -> startActivity(new Intent(this, Lessons.class).putExtra("USERNAME", username)));
        dailyChallenges.setOnClickListener(view -> startActivity(new Intent(this, DailyChallenges.class).putExtra("USERNAME", username)));
        settings.setOnClickListener(view -> startActivity(new Intent(this, Settings.class).putExtra("USERNAME", username)));

        // Update level display on activity start
        updateUserLevelDisplay(getTotalDuration(username));
    }

    @Override
    protected void onResume() {
        super.onResume();
        SessionManager.startSession();
    }

    @Override
    protected void onPause() {
        super.onPause();
        SessionManager.endSession();
        long duration = SessionManager.getSessionDuration();
        long totalDuration = updateTotalDuration(username, duration);
        updateUserLevelDisplay(totalDuration);
        Log.d("Session Duration", "Duration: " + duration + " milliseconds");
    }

    private long updateTotalDuration(String username, long additionalDuration) {
        String filename = "userDurations.json";
        JSONObject durations;
        long newTotalDuration = additionalDuration;

        try (FileInputStream fis = openFileInput(filename)) {
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            String json = new String(buffer, StandardCharsets.UTF_8);
            durations = new JSONObject(json);
            newTotalDuration += durations.optLong(username, 0);
        } catch (IOException | JSONException e) {
            durations = new JSONObject();
        }

        try {
            durations.put(username, newTotalDuration);
            try (FileOutputStream fos = openFileOutput(filename, MODE_PRIVATE)) {
                fos.write(durations.toString().getBytes(StandardCharsets.UTF_8));
            }
        } catch (IOException | JSONException e) {
            Log.e("Save Duration Error", "Failed to save user duration", e);
        }

        return newTotalDuration;
    }

    private void updateUserLevelDisplay(long totalDuration) {
        long level = totalDuration / (60 * 60 * 1000);  // Example: 1 level per hour of usage
        userLevelTextView.setText("Level: " + level);
        Log.d("User Level", "User " + username + " is at level " + level);
    }

    private long getTotalDuration(String username) {
        String filename = "userDurations.json";
        try (FileInputStream fis = openFileInput(filename)) {
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            String json = new String(buffer, StandardCharsets.UTF_8);
            JSONObject durations = new JSONObject(json);
            return durations.optLong(username, 0);
        } catch (IOException | JSONException e) {
            Log.e("Read Duration Error", "Failed to read user durations", e);
        }
        return 0;
    }
}
