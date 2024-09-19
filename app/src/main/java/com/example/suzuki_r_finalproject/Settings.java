package com.example.suzuki_r_finalproject;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import java.util.Calendar;

public class Settings extends AppCompatActivity {

    private Switch themeSwitch;
    private Spinner notificationPreferenceSpinner;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private String username;
    private EditText newUsernameEditText, newPasswordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        username = getIntent().getStringExtra("USERNAME");
        themeSwitch = findViewById(R.id.themeSwitch);
        notificationPreferenceSpinner = findViewById(R.id.notificationPreferenceSpinner);
        newUsernameEditText = findViewById(R.id.newUsernameEditText);
        newPasswordEditText = findViewById(R.id.newPasswordEditText);

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("userPreferences", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        // Set the switch state based on the saved preference
        boolean isDarkMode = sharedPreferences.getBoolean("darkMode", false);
        themeSwitch.setChecked(isDarkMode);

        // Apply the theme based on the saved preference
        if (isDarkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        // Set a listener on the switch to save the preference when changed
        themeSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            editor.putBoolean("darkMode", isChecked);
            editor.apply();

            // Apply the theme change
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }

            // Recreate the activity to apply the theme change
            recreate();
        });

        // Set the saved notification preference
        String notificationPreference = sharedPreferences.getString("notificationPreference", "Daily");
        switch (notificationPreference) {
            case "Weekly":
                notificationPreferenceSpinner.setSelection(1);
                break;
            case "Monthly":
                notificationPreferenceSpinner.setSelection(2);
                break;
            default:
                notificationPreferenceSpinner.setSelection(0);
                break;
        }

        // Handle the Save button click to save notification preferences
        Button saveNotificationPreferences = findViewById(R.id.saveNotificationPreferences);
        saveNotificationPreferences.setOnClickListener(v -> {
            String selectedPreference = notificationPreferenceSpinner.getSelectedItem().toString();
            editor.putString("notificationPreference", selectedPreference);
            editor.apply();
            scheduleNotification(Settings.this);  // Ensure notifications are rescheduled when preferences change
            Toast.makeText(Settings.this, "Notification preference saved", Toast.LENGTH_SHORT).show();
        });

    }

    private void scheduleNotification(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, ReminderBroadcastReceiver.class);
        PendingIntent alarmIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_IMMUTABLE);

        // Set the alarm to start at approximately 2:00 p.m.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 14);

        // With setInexactRepeating(), you have to use one of the AlarmManager interval
        // constants--in this case, AlarmManager.INTERVAL_DAY.
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY, alarmIntent);
    }

    // Handle the Go Back button click
    public void handleGoBackButton(View view) {
        finish(); // Close the settings activity and return to the previous screen
    }

    // Handle the Change Username button click
    public void onChangeUsernameClicked(View view) {
        String newUsername = newUsernameEditText.getText().toString();
        if (newUsername.isEmpty()) {
            Toast.makeText(this, "New username cannot be empty!", Toast.LENGTH_SHORT).show();
            return;
        }

        SharedPreferences prefs = getSharedPreferences("UserAccounts", MODE_PRIVATE);
        String existingUser = prefs.getString(newUsername, null);
        if (existingUser != null) {
            // Username already exists
            Toast.makeText(this, "Username already exists! Try another one.", Toast.LENGTH_SHORT).show();
            return;
        }

        SharedPreferences.Editor userEditor = prefs.edit();
        userEditor.putString(newUsername, prefs.getString(username, ""));
        userEditor.remove(username);
        userEditor.apply();

        // Update username-related preferences
        SharedPreferences userFeatures = getSharedPreferences("UserFeatures", MODE_PRIVATE);
        SharedPreferences.Editor featuresEditor = userFeatures.edit();
        featuresEditor.putString(newUsername + "_proficiency", userFeatures.getString(username + "_proficiency", ""));
        featuresEditor.remove(username + "_proficiency");
        featuresEditor.apply();

        username = newUsername;
        Toast.makeText(this, "Username changed successfully!", Toast.LENGTH_SHORT).show();
    }

    // Handle the Change Password button click
    public void onChangePasswordClicked(View view) {
        String newPassword = newPasswordEditText.getText().toString();
        if (newPassword.isEmpty()) {
            Toast.makeText(this, "New password cannot be empty!", Toast.LENGTH_SHORT).show();
            return;
        }

        SharedPreferences prefs = getSharedPreferences("UserAccounts", MODE_PRIVATE);
        SharedPreferences.Editor userEditor = prefs.edit();
        userEditor.putString(username, newPassword);
        userEditor.apply();

        Toast.makeText(this, "Password changed successfully!", Toast.LENGTH_SHORT).show();
    }
}
