package com.example.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView timer;
    TextView score;
    TextView question;
    TextView popup;
    android.support.v7.widget.GridLayout gridLayout;
    ArrayList<Integer> arr = new ArrayList<Integer>();
    ArrayList<Integer> answers = new ArrayList<Integer>();
    TextView o1;
    TextView o2;
    TextView o3;
    TextView o4;
    int num1,num2,num,deno;
    Button playButton;
    boolean gameIsOver = false;


    CountDownTimer countDownTimer = new CountDownTimer(30000 + 100,1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            timer.setText(Integer.toString((int)millisUntilFinished/1000));
        }

        @Override
        public void onFinish() {
            gameIsOver = true;
            playButton.setEnabled(true);
            playButton.setText("Play Again");
            popup.setText("You Scored " + Integer.toString(num) + "/" +  Integer.toString(deno));
            popup.setVisibility(View.VISIBLE);
            num=0;deno=0;
            question.setText("Play Again?");
            gridLayout.setVisibility(View.INVISIBLE);
        }
    };

    public void generateRandomNumbers()
    {
        Random rand = new Random();
        num1 = rand.nextInt(101);
        num2 = rand.nextInt(101);
        question.setText(Integer.toString(num1) + "+" + Integer.toString(num2));
        for(int i=0; i<4; i++)
            arr.add(i);
        Collections.shuffle(arr);

        int op1 = num1+num2;
        int op2 = num1 + rand.nextInt(100);
        int op3 = num2 + rand.nextInt(101);
        int op4 = (1+rand.nextInt(11)) * (1+rand.nextInt(21));

        answers.add(op1);
        answers.add(op2);
        answers.add(op3);
        answers.add(op4);
        Collections.shuffle(answers);

        o1.setText(Integer.toString(answers.get(0)));
        o2.setText(Integer.toString(answers.get(1)));
        o3.setText(Integer.toString(answers.get(2)));
        o4.setText(Integer.toString(answers.get(3)));

        answers.clear();
        arr.clear();
    }


    public void nextQuestion(View view){

        if(!gameIsOver) {
            TextView clicked = (TextView) view;

            String a = String.valueOf(clicked.getText());
            int ans = Integer.parseInt(a);

            if (ans == num1 + num2) {
                num++;
            }
            deno++;

            score.setText(Integer.toString(num) + "/" + Integer.toString(deno));

            generateRandomNumbers();
        }
    }

    public void startGame(View view){

        countDownTimer.start();
        generateRandomNumbers();
        gameIsOver = false;
        playButton.setEnabled(false);
        score.setText(Integer.toString(num) + "/" + Integer.toString(deno));
        popup.setVisibility(View.INVISIBLE);
        gridLayout.setVisibility(View.VISIBLE);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timer = (TextView) findViewById(R.id.timer);
        question = (TextView) findViewById(R.id.question);
        popup = (TextView)findViewById(R.id.popup);
        score = (TextView) findViewById(R.id.score);
        o1 = (TextView) findViewById(R.id.option1);
        o2 = (TextView) findViewById(R.id.option2);
        o3 = (TextView) findViewById(R.id.option3);
        o4 = (TextView) findViewById(R.id.option4);

        gridLayout = (android.support.v7.widget.GridLayout) findViewById(R.id.GridLayout);

        playButton = (Button) findViewById(R.id.button);

        num=0;deno=0;
    }
}
