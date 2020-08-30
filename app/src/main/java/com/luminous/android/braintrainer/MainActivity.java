package com.luminous.android.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private Button startButton;
    private TextView quizTextView;
    private ArrayList<Integer> answerList = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = findViewById(R.id.startButton);
        quizTextView = findViewById(R.id.quizText);
    }

    public void startGame(View view) {
        startButton.setVisibility(View.INVISIBLE);
    }

    public void quizUISetUp() {
        int x = new Random().nextInt(99);
        int y = new Random().nextInt(99);

        int correctAnswerIndex = new Random().nextInt(4);

        quizTextView.setText(MessageFormat.format("{0} + {1}", x, y));

        for (int i = 0; i < 4; i++) {
            if(i == correctAnswerIndex) {
                answerList.add(x + y);
            } else {
                answerList.add(new Random().nextInt(99) + new Random().nextInt(99));
            }
        }

    }
}