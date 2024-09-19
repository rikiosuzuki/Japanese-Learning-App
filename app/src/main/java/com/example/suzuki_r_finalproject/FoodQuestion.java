package com.example.suzuki_r_finalproject;

public class FoodQuestion {
    int imageResId;
    String[] options;
    String correctAnswer;

    public FoodQuestion(int imageResId, String[] options, String correctAnswer) {
        this.imageResId = imageResId;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    public int getImageResId(){
        return imageResId;
    }
    public String[] getOptions(){
        return options;
    }
    public String getCorrectAnswer(){
        return correctAnswer;
    }

}

