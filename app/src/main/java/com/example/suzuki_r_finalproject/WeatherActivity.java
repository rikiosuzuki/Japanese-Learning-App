package com.example.suzuki_r_finalproject;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherActivity extends AppCompatActivity {

    private static final String API_KEY = "65701d69363f7af5d424c3d189d48dcb";
    private static final String WEATHER_URL = "https://api.openweathermap.org/data/2.5/weather?q=Tokyo&appid=" + API_KEY + "&units=metric&lang=ja";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        new FetchWeatherTask().execute();
    }

    public void handleGoBack(View view) {
        finish();
    }

    private class FetchWeatherTask extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... voids) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL(WEATHER_URL);
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();

                // Check for successful response code or throw error
                if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                    return null;
                }

                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }
                return stringBuilder.toString();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        @Override
        protected void onPostExecute(String response) {
            super.onPostExecute(response);
            if (response != null) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONObject main = jsonObject.getJSONObject("main");
                    double temperature = main.getDouble("temp");
                    String description = jsonObject.getJSONArray("weather").getJSONObject(0).getString("description");

                    // Update UI elements
                    TextView tvTemperature = findViewById(R.id.tvTemperature);
                    TextView tvWeatherDescription = findViewById(R.id.tvWeatherDescription);

                    // Set the text of the TextViews to show the temperature and description
                    tvTemperature.setText(getString(R.string.temperature_output, temperature));
                    tvWeatherDescription.setText(getString(R.string.description_output, description));
                } catch (Exception e) {
                    e.printStackTrace();
                    // Optionally handle errors or bad data
                    Toast.makeText(WeatherActivity.this, "Failed to parse weather data.", Toast.LENGTH_SHORT).show();
                }
            } else {
                // Handle error or no data scenario
                Toast.makeText(WeatherActivity.this, "No response from weather service.", Toast.LENGTH_SHORT).show();
            }
        }

    }
}
