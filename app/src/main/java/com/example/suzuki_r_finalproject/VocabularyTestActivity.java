package com.example.suzuki_r_finalproject;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class VocabularyTestActivity extends AppCompatActivity {

    private RadioGroup radioGroup, radioGroup2, radioGroup3, radioGroup4, radioGroup5, radioGroup6;
    private RadioButton option1, option2, option3, option4, option5, option6, option7, option8, option9, option10, option11, option12, option13, option14, option15, option16;
    private TextView questionText, scoreTextView;
    private String username;

    private int score =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocabulary_test);
        radioGroup = findViewById(R.id.answers_group);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);
        radioGroup2 = findViewById(R.id.answers_group2);
        option5 = findViewById(R.id.option5);
        option6 = findViewById(R.id.option6);
        option7 = findViewById(R.id.option7);
        option8 = findViewById(R.id.option8);
        radioGroup3 = findViewById(R.id.answers_group3);
        option9 = findViewById(R.id.option9);
        option10 = findViewById(R.id.option10);
        option11 = findViewById(R.id.option11);
        option12 = findViewById(R.id.option12);
        option13 = findViewById(R.id.option13);
        option14 = findViewById(R.id.option14);
        option15 = findViewById(R.id.option15);
        option16 = findViewById(R.id.option16);
        radioGroup4 = findViewById(R.id.answers_group4);
        radioGroup5 = findViewById(R.id.answers_group5);
        radioGroup6 = findViewById(R.id.answers_group6);
        username = getIntent().getStringExtra("USERNAME");

        questionText = findViewById(R.id.question_text);
        scoreTextView = findViewById(R.id.scoreTextView);
    }

    public void submitAnswer(View view) {

        int selectedId = radioGroup.getCheckedRadioButtonId();
        RadioButton selectedRadioButton = findViewById(selectedId);
        if(selectedRadioButton.getText().equals("猫（ねこ）")){
            score++;
        }
        int selectedId2 = radioGroup2.getCheckedRadioButtonId();
        RadioButton selectedRadioButton2 = findViewById(selectedId2);
        if(selectedRadioButton2.getText().equals("椅子（いす）")){
            score++;
        }
        int selectedId3 = radioGroup3.getCheckedRadioButtonId();
        RadioButton selectedRadioButton3 = findViewById(selectedId3);
        if(selectedRadioButton3.getText().equals("水ボトル")){
            score++;
        }
        int selectedId4 = radioGroup4.getCheckedRadioButtonId();
        RadioButton selectedRadioButton4 = findViewById(selectedId4);
        if(selectedRadioButton4.getText().equals("true")){
            score++;
        }
        int selectedId5 = radioGroup5.getCheckedRadioButtonId();
        RadioButton selectedRadioButton5 = findViewById(selectedId5);
        if(selectedRadioButton5.getText().equals("false")){
            score++;
        }

        int selectedId6 = radioGroup6.getCheckedRadioButtonId();
        RadioButton selectedRadioButton6 = findViewById(selectedId6);
        if(selectedRadioButton6.getText().equals("false")){
            score++;
        }


        scoreTextView.setText("Your score is: " + score + "/6");

        saveScoreToJson(username, score);

    }
    private void saveScoreToJson(String username, int score) {
        String filename = "testScores.json";
        JSONArray scoresArray;
        boolean scoreUpdated = false;

        // Read existing scores from the file
        try (FileInputStream fis = openFileInput(filename)) {
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            String json = new String(buffer, StandardCharsets.UTF_8);
            scoresArray = new JSONArray(json);

            // Iterate over existing scores to check for existing username
            for (int i = 0; i < scoresArray.length(); i++) {
                JSONObject scoreObject = scoresArray.getJSONObject(i);
                if (scoreObject.getString("username").equals(username)) {
                    // Check if the current score is higher than the saved one
                    if (scoreObject.getInt("vocabularyTestScore") < score) {
                        scoreObject.put("vocabularyTestScore", score); // Update with the new higher score
                    }
                    scoreUpdated = true; // Mark that the score has been updated
                    break; // No need to check further
                }
            }
        } catch (IOException | JSONException e) {
            scoresArray = new JSONArray(); // If file does not exist or is empty, start with a new array
            showToast("Creating a new score file or reading failed. Starting fresh.");
        }

        // If no existing score was found for the user, add a new entry
        if (!scoreUpdated) {
            try {
                JSONObject newScoreObject = new JSONObject();
                newScoreObject.put("username", username);
                newScoreObject.put("vocabularyTestScore", score);
                scoresArray.put(newScoreObject);
            } catch (JSONException e) {
                e.printStackTrace();
                showToast("Failed to create a new score entry.");
            }
        }

        // Write the (potentially updated) scores array back to the file
        try (FileOutputStream fos = openFileOutput(filename, Context.MODE_PRIVATE)) {
            fos.write(scoresArray.toString().getBytes(StandardCharsets.UTF_8));
            showToast("Score saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            showToast("Failed to save score.");
        }
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void handleGoBack(View view) {
        finish();
    }
}