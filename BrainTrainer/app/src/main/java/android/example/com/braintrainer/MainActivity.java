package android.example.com.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.support.v7.widget.GridLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

import static java.util.Arrays.asList;

public class MainActivity extends AppCompatActivity {

    Button startButton, playAgainButton;
    GridLayout gridLayout;
    TextView score, question , time, gameOver, answerText;
    int totalOfQuestions = 0, totalScore = 0;
    ArrayList<Integer> answers;
    Integer rightAnswer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        startButton = findViewById(R.id.startButton);
        score = findViewById(R.id.scoreTextView);
        question = findViewById(R.id.questionTextView);
        gridLayout = findViewById(R.id.gridLayout);
        time = findViewById(R.id.timeTextView);
        playAgainButton = findViewById(R.id.playAgainButton);
        gameOver = findViewById(R.id.gameOverTextView);
        answerText = findViewById(R.id.answerTextView);

        score.setText(Integer.toString(totalScore) + "/" + Integer.toString(totalOfQuestions));
    }

    public void startGame(View view){
        setGameVisible();
        startTimer();
        generateNewQuestion();
    }

    public void playAgain(View view){
        setGameInvisible();
        answerText.setVisibility(View.INVISIBLE);

    }

    public void setGameVisible(){
        score.setVisibility(View.VISIBLE);
        gridLayout.setVisibility(View.VISIBLE);
        question.setVisibility(View.VISIBLE);
        time.setVisibility(View.VISIBLE);
        startButton.setVisibility(View.INVISIBLE);
    }

    public void setGameInvisible(){
        score.setVisibility(View.INVISIBLE);
        gridLayout.setVisibility(View.INVISIBLE);
        question.setVisibility(View.INVISIBLE);
        time.setVisibility(View.INVISIBLE);
        startButton.setVisibility(View.VISIBLE);
        playAgainButton.setVisibility(View.INVISIBLE);
        gameOver.setVisibility(View.INVISIBLE);
    }

    public void startTimer(){
        enablingButtons(gridLayout);
        new CountDownTimer(30000,1000){
            @Override
            public void onTick(long millisUntilFinished) {
                long seconds = millisUntilFinished / 1000;
                String timerValue = Long.toString(seconds);

                time.setText(timerValue + "s");
            }

            @Override
            public void onFinish() {
                playAgainButton.setVisibility(View.VISIBLE);
                gameOver.setVisibility(View.VISIBLE);
                disableButtons(gridLayout);
            }
        }.start();
    }

    public void generateNewQuestion(){
        Random generator = new Random();

        //answerText.setVisibility(View.INVISIBLE);

        int firstQuestionNumber = generator.nextInt(60) + 1;
        int secondQuestionNumber = generator.nextInt(60) + 1;

        int base = (firstQuestionNumber > secondQuestionNumber) ? firstQuestionNumber : secondQuestionNumber;


        int answerNumber1 = firstQuestionNumber + secondQuestionNumber;
        int answerNumber2 = generator.nextInt(60 ) + base;
        int answerNumber3 = generator.nextInt(60 ) + base;
        int answerNumber4 = generator.nextInt(60 ) + base;

        rightAnswer = answerNumber1;

        answers = new ArrayList<>(asList(answerNumber1,answerNumber2,answerNumber3,answerNumber4));
        Collections.shuffle(answers);

        question.setText(Integer.toString(firstQuestionNumber) + " + " + Integer.toString(secondQuestionNumber));

        for(int i =0 ; i < answers.size() ; i++){
            Button answerButton = (Button) gridLayout.getChildAt(i);
            answerButton.setText(String.valueOf(answers.get(i)));
        }
    }

    public void chooseAnswer(View view){
        int clicked =  Integer.parseInt(view.getTag().toString());
        int clickedAnswer = answers.get(clicked);


        if(clickedAnswer == rightAnswer){
            answerText.setText("Right :) ");
            totalScore++;
        } else {
            answerText.setText("Wrong :)  is was" + rightAnswer );
        }
        totalOfQuestions++;
        score.setText( String.valueOf(totalScore) + "/" + String.valueOf(totalOfQuestions) );
        answerText.setVisibility(View.VISIBLE);
        generateNewQuestion();
    }

    private void disableButtons(GridLayout layout){
        for(int i = 0 ; i < layout.getChildCount() ; i++){
            View child = layout.getChildAt(i);
            child.setEnabled(false);
        }
    }
    private void enablingButtons(GridLayout layout){
        for(int i = 0 ; i < layout.getChildCount() ; i++){
            View child = layout.getChildAt(i);
            child.setEnabled(true);
        }
    }
}
