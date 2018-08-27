package com.example.whichnumberisbigger;

import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView textViewScore;
    private Button buttonLeft;
    private Button buttonRight;
    private ConstraintLayout background;
    private int score;
    private int leftNum;
    private int rightNum;

    public static final int MAX_NUM = 1000;
    public static final int MIN_NUM = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        score = 0;
        wireWidgets();
        randomizeAndUpdateDisplay();

    }

    private void randomizeAndUpdateDisplay() {
        String scoreString = getResources().getString((R.string.textview_main_score));
        textViewScore.setText(scoreString + score);
        randomizeNumbers();
        buttonLeft.setText(String.valueOf(leftNum));
        buttonRight.setText(String.valueOf(rightNum));

    }

    private void randomizeNumbers() {
        leftNum = genNumber();
        rightNum = genNumber();
        while (leftNum == rightNum) {
            leftNum = genNumber();
        }
    }

    private int genNumber() {
        int range = MAX_NUM - MIN_NUM + 1;
        return MIN_NUM + (int) (Math.random() * range);
    }

    private void wireWidgets() {
        textViewScore = findViewById(R.id.textview_main_score);
        buttonLeft = findViewById(R.id.buttonLeft_main_press);
        buttonRight = findViewById(R.id.buttonRight_main_press);
        background = findViewById(R.id.constraint_main_background);
    }

    public void onLeftClick(View view) {
        checkAnswer(true);
    }

    public void onRightClick(View view) {
        checkAnswer(false);
    }

    public void checkAnswer(boolean leftPressed) {
        String message;
        if ((rightNum > leftNum && leftPressed) ||
                rightNum > leftNum && !leftPressed){
            score++;
            textViewScore.setText(String.valueOf(score));
            message = "Good Job";
            background.setBackgroundColor(Color.rgb(0, 255, 0));
        }
        else{
            score--;
            textViewScore.setText(String.valueOf(score));
            message = "Try Again";
            background.setBackgroundColor(Color.rgb(255, 0, 0));
        }
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        randomizeAndUpdateDisplay();
    }
}

