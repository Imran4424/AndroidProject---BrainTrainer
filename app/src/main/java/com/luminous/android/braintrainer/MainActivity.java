package com.luminous.android.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private Button startButton;
    private Button playAgain;
    private ConstraintLayout quizConstraintLayout;
    private GridLayout quizGridLayout;
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
        playAgain = findViewById(R.id.playAgain);
        quizTextView = findViewById(R.id.quizText);
        scoreText = findViewById(R.id.scoreText);
        timerText = findViewById(R.id.timerText);
        resultText = findViewById(R.id.resultText);
        quizConstraintLayout = findViewById(R.id.quizConstraint);
        quizGridLayout = findViewById(R.id.quizGridLayout);

        quizConstraintLayout.setVisibility(View.INVISIBLE);
    }

    public void startGame(View view) {
        startButton.setVisibility(View.INVISIBLE);
        playAgain.setVisibility(View.INVISIBLE);
        quizConstraintLayout.setVisibility(View.VISIBLE);
        resultText.setText("");
        setUpCountDownTimer(30);
        quizUISetUp();
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
                resultText.setText("Your score: " + score);
                disableButtons(quizGridLayout);
                playAgain.setVisibility(View.VISIBLE);
            }
        }.start();
    }

    public void playAgain(View view) {
        score = 0;
        totalQuestion = 0;
        scoreText.setText(score + "/" + totalQuestion);
        setUpCountDownTimer(30);
        startButton.setVisibility(View.INVISIBLE);
        playAgain.setVisibility(View.INVISIBLE);
        enableButtons(quizGridLayout);
        quizConstraintLayout.setVisibility(View.VISIBLE);
        resultText.setText("");
        quizUISetUp();
    }

    private void disableButtons(GridLayout layout) {

        // Get all touchable views
        ArrayList<View> layoutButtons = layout.getTouchables();

        // loop through them, if they are an instance of Button, disable it.
        for(View v : layoutButtons){
            if( v instanceof Button ) {
                ((Button)v).setEnabled(false);
            }
        }
    }

    private void enableButtons(GridLayout layout) {

        // Get all touchable views
        ArrayList<View> layoutButtons = layout.getTouchables();

        // loop through them, if they are an instance of Button, enable it.
        for(View v : layoutButtons){
            if( v instanceof Button ) {
                ((Button)v).setEnabled(true);
            }
        }
    }
}