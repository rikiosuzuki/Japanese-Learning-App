package com.example.suzuki_r_finalproject;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;

public class VocabMatchingGameActivity extends AppCompatActivity {
    LinearLayout wordsColumn, definitionsColumn;
    TextView selectedWord;
    int totalWords, correctMatches = 0; // To keep track of the total words and correctly matched pairs


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocab_matching_game);

        wordsColumn = findViewById(R.id.wordsColumn);
        definitionsColumn = findViewById(R.id.definitionsColumn);

        setupGame();
    }

    private void setupGame() {
        String[] words = {"襲う", "目立つ", "生まれる", "喜ぶ", "迷う", "驚く", "濡れる", "始まる", "答える"};
        String[] definitions = {"to attack", "to stand out", "to be born", "to be glad", "to be puzzled","to be surprised", "to be wet", "to start", "to answer"};

        ArrayList<TextView> definitionViews = new ArrayList<>();
        totalWords = words.length; // Set total words count

        for (int i = 0; i < words.length; i++) {
            TextView wordView = createTextView(words[i], true);
            TextView definitionView = createTextView(definitions[i], false);
            definitionView.setTag(words[i]); // Tag each definition with the correct word for matching
            definitionViews.add(definitionView);
            wordsColumn.addView(wordView);
        }

        // Shuffle the list of definition views
        Collections.shuffle(definitionViews);

        // Add the shuffled definitions to the view
        for (TextView definitionView : definitionViews) {
            definitionsColumn.addView(definitionView);
        }

    }

    private TextView createTextView(String text, boolean isWord) {
        TextView textView = new TextView(this);
        textView.setText(text);
        textView.setPadding(10, 10, 10, 10);
        textView.setTextColor(Color.BLACK);
        textView.setBackgroundResource(android.R.drawable.dialog_holo_light_frame);
        if (isWord) {
            textView.setOnClickListener(wordClickListener);
        } else {
            textView.setOnClickListener(definitionClickListener);
        }
        return textView;
    }

    View.OnClickListener wordClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (selectedWord != null) {
                selectedWord.setBackgroundColor(Color.TRANSPARENT); // Reset the previous selection
            }
            selectedWord = (TextView) v;
            selectedWord.setBackgroundColor(Color.LTGRAY); // Highlight the selected word
        }
    };

    View.OnClickListener definitionClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            TextView definitionView = (TextView) v;
            if (selectedWord != null && definitionView.getTag().equals(selectedWord.getText().toString())) {
                definitionView.setBackgroundColor(Color.GREEN); // Correct match
                selectedWord.setVisibility(View.GONE); // Hide the word as it's correctly matched
                correctMatches++;
                selectedWord = null; // Reset the selection
                if(correctMatches == totalWords){
                    displayCompletionMessage();
                }
            } else if (selectedWord != null) {
                definitionView.setBackgroundColor(Color.RED); // Incorrect match
            }
        }
    };
    private void displayCompletionMessage() {
        Toast.makeText(this, "Congratulations! All items matched!", Toast.LENGTH_LONG).show();
    }

    public void handleGoBack(View view) {
        finish();
    }
}
