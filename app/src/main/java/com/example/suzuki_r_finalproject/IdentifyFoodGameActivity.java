package com.example.suzuki_r_finalproject;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.suzuki_r_finalproject.FoodQuestion;

import java.util.ArrayList;
import java.util.List;

public class IdentifyFoodGameActivity extends AppCompatActivity {
    ImageView foodImage;
    Button[] choices = new Button[4];
    List<FoodQuestion> questions;
    int currentQuestionIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identify_food_game);

        foodImage = findViewById(R.id.foodImage);
        choices[0] = findViewById(R.id.choice1);
        choices[1] = findViewById(R.id.choice2);
        choices[2] = findViewById(R.id.choice3);
        choices[3] = findViewById(R.id.choice4);

        setupQuestions();
        loadQuestion();
    }

    private void setupQuestions() {
        questions = new ArrayList<>();
        questions.add(new FoodQuestion(R.drawable.img_5, new String[]{"寿司", "蟹", "虫", "ご飯"}, "寿司"));
        questions.add(new FoodQuestion(R.drawable.img_6, new String[]{"犬", "雨", "田舎", "もち"}, "もち"));
        questions.add(new FoodQuestion(R.drawable.img_7, new String[]{"箱", "オレンジ", "桃", "りんご"}, "桃"));
        questions.add(new FoodQuestion(R.drawable.img_8, new String[]{"さくらんぼ", "オレンジ", "寿司", "りんご"}, "さくらんぼ"));
        questions.add(new FoodQuestion(R.drawable.img_9, new String[]{"こま", "おにぎり", "桃", "りんご"}, "おにぎり"));

        // Add more questions as needed
    }

    private void loadQuestion() {
        FoodQuestion currentQuestion = questions.get(currentQuestionIndex);
        foodImage.setImageResource(currentQuestion.getImageResId());
        for (int i = 0; i < choices.length; i++) {
            choices[i].setText(currentQuestion.getOptions()[i]);
            choices[i].setOnClickListener(v -> checkAnswer(((Button) v).getText().toString()));
        }
    }

    private void checkAnswer(String selectedAnswer) {
        FoodQuestion currentQuestion = questions.get(currentQuestionIndex);
        if (selectedAnswer.equals(currentQuestion.getCorrectAnswer())) {
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
            currentQuestionIndex++; // Move to next question
            if (currentQuestionIndex < questions.size()) {
                loadQuestion(); // Load the next question
            } else {
                Toast.makeText(this, "Congratulations! You've completed all questions!", Toast.LENGTH_LONG).show();
                currentQuestionIndex = 0; // Reset or finish the activity
                loadQuestion(); // Start over or provide options to restart or quit
            }
        } else {
            Toast.makeText(this, "Wrong! Try again!", Toast.LENGTH_SHORT).show();
        }
    }

    public void handleGoBack(View view) {
        finish();
    }
}
