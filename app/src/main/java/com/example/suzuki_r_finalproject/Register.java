package com.example.suzuki_r_finalproject;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;

public class Register extends AppCompatActivity {

    EditText usernameEditText, passwordEditText;
    TextView proficiencyTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        proficiencyTextView = findViewById(R.id.proficiency);

    }

    public void onRegisterClicked(View view) {
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        SharedPreferences sharedPreferences = getSharedPreferences("UserFeatures", MODE_PRIVATE);
        SharedPreferences.Editor editor2 = sharedPreferences.edit();
        // Repeat for other fields
        String proficiency = proficiencyTextView.getText().toString();

        if (username.isEmpty() || password.isEmpty() || proficiency.isEmpty()) {
            // Show error message
            return;
        }
        SharedPreferences prefs = getSharedPreferences("UserAccounts", MODE_PRIVATE);
        String existingUser = prefs.getString(username, null);
        if (existingUser != null) {
            // Username exists, show error
            Toast.makeText(this, "Username already exists! Try again!", Toast.LENGTH_SHORT).show();
            return;
        }
        editor2.putString(username+ "_proficiency", proficiency);
        editor2.apply();
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(username, password); // Store username and password, similarly store other details
        editor.apply();

        // Proceed to next activity
        goToSecondActivity(username);
    }


    private void goToSecondActivity(String username){
        Intent intent = new Intent(this, Dashboard.class);
        intent.putExtra("USERNAME", username);
        startActivity(intent);
    }

    public void onGoBackClicked(View view) {
        finish();
    }

    public void onButtonLevel1Clicked(View view) {
        proficiencyTextView.setText("Level 1");

    }

    public void onButtonLevel2Clicked(View view) {
        proficiencyTextView.setText("Level 2");
    }

    public void onButtonLevel3Clicked(View view) {
        proficiencyTextView.setText("Level 3");
    }
}