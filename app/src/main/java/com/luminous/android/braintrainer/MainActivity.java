package com.luminous.android.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.MessageFormat;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private Button startButton;
    private TextView quizTextView;
    private TextView scoreText;
    private TextView timerText;
    private TextView resultText;
    private int correctAnswer = 0;
    int score = 0;
    int totalQuestion = 0;
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
        resultText = findViewById(R.id.resultText);

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
                ((Button)findViewById(buttonIds[i])).setText(Integer.toString((correctAnswer)));
            } else {
                int wrongAnswer = new Random().nextInt(200);
                while (wrongAnswer == correctAnswer) {
                    wrongAnswer = new Random().nextInt(200);
                }

                ((Button)findViewById(buttonIds[i])).setText(Integer.toString(wrongAnswer));
            }

            ((Button)findViewById(buttonIds[i])).setTextSize(60);
        }
    }

    public void evaluateQuiz(View view) {
        if(((Button) view).getText().toString().equals(Integer.toString(correctAnswer))) {
            resultText.setText("Correct!");
            score++;
        } else {
            resultText.setText("Wrong :(");
        }

        totalQuestion++;

        scoreText.setText(score + "/" + totalQuestion);

        quizUISetUp();
    }

    public void setUpCountDownTimer(int seconds) {
        new CountDownTimer(seconds * 1000 + 100, 1000) {

            @Override
            public void onTick(long miliSeconds) {
                timerText.setText((miliSeconds/1000) + "s");
            }

            @Override
            public void onFinish() {

            }
        }.start();
    }
}