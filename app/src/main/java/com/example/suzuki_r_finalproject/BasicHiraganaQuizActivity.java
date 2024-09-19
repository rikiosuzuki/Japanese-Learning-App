package com.example.suzuki_r_finalproject;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.RadioGroup;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class BasicHiraganaQuizActivity extends AppCompatActivity {
    private RadioGroup radioGroup, radioGroup2, radioGroup3;
    private RadioButton option1, option2, option3, option4, option5, option6, option7, option8, option9, option10, option11, option12;
    private TextView questionText, scoreTextView;

    private int score =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_hiragana_quiz);

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

        questionText = findViewById(R.id.question_text);
        scoreTextView = findViewById(R.id.scoreTextView);

    }

    public void submitAnswer(View view) {

        int selectedId = radioGroup.getCheckedRadioButtonId();
        RadioButton selectedRadioButton = findViewById(selectedId);
        if(selectedRadioButton.getText().equals("あ")){
            score++;
        }
        int selectedId2 = radioGroup2.getCheckedRadioButtonId();
        RadioButton selectedRadioButton2 = findViewById(selectedId2);
        if(selectedRadioButton2.getText().equals("し")){
            score++;
        }
        int selectedId3 = radioGroup3.getCheckedRadioButtonId();
        RadioButton selectedRadioButton3 = findViewById(selectedId3);
        if(selectedRadioButton3.getText().equals("を")){
            score++;
        }
        scoreTextView.setText("Your score is: " + score + "/3");


    }

    public void handleGoBack(View view) {
        finish();
    }
}