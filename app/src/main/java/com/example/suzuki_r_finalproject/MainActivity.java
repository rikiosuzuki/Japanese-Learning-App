package com.example.suzuki_r_finalproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText usernameEditText;
    EditText passwordEditText;
    Button register, login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        register = findViewById(R.id.btnRegister);
        login = findViewById(R.id.btnLogin);
    }


    public void onLoginClicked(View view) {
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        SharedPreferences prefs = getSharedPreferences("UserAccounts", MODE_PRIVATE);
        String registeredPassword = prefs.getString(username, null);

        if (registeredPassword != null && registeredPassword.equals(password)) {
            // Login successful
            goToSecondActivity(username);
        } else {
            // Login failed, show error
            Toast.makeText(this, "Wrong password or username!", Toast.LENGTH_SHORT).show();
        }
    }

    public void onRegisterClicked(View view) {
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }

    private void goToSecondActivity(String username){
        Intent intent = new Intent(this, Dashboard.class);
        intent.putExtra("USERNAME", username);
        startActivity(intent);
    }

}