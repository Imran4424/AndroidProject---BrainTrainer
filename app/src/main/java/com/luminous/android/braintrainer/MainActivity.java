package com.luminous.android.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeoutException;

public class MainActivity extends AppCompatActivity {
    private Button startButton;
    private TextView quizTextView;
    private TextView scoreText;
    private TextView timerText;
    private int correctAnswer = 0;
    private int[] buttonIds = {R.id.optionOneButton,
            R.id.optionTwoButton,
            R.id.optionThreeButton,
            R.id.optionFourButton};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = findViewById(R.id.startButton);
        quizTextView = findViewById(R.id.quizText);
        scoreText = findViewById(R.id.scoreText);
        timerText = findViewById(R.id.timerText);

        quizUISetUp();
    }

    public void startGame(View view) {
        startButton.setVisibility(View.INVISIBLE);
    }

    public void quizUISetUp() {
        int x = new Random().nextInt(100);
        int y = new Random().nextInt(100);
        correctAnswer = x + y;

        int correctAnswerIndex = new Random().nextInt(4);

        quizTextView.setText(MessageFormat.format("{0} + {1}", x, y));

        for (int i = 0; i < 4; i++) {
            if(i == correctAnswerIndex) {
                ((Button)findViewById(buttonIds[i])).setText(Integer.toString((x + y)));
            } else {
                int wrongAnswer = new Random().nextInt(200);
                while (wrongAnswer == correctAnswer) {
                    wrongAnswer = 
                }

                ((Button)findViewById(buttonIds[i])).setText(Integer.toString(wrongAnswer));
            }

            ((Button)findViewById(buttonIds[i])).setTextSize(60);
        }
    }

    public void evaluateQuiz(View view) {
        if(((Button) view).getText().toString().equals(Integer.toString(correctAnswer))) {

        }
    }

    public void updateScore(int score, int totalQuestion) {

    }
}