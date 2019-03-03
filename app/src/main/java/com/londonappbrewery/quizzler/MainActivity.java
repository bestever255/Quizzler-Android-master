package com.londonappbrewery.quizzler;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {


    Button trueButton;
    Button falseButton;
    TextView questionTextView;
    TextView scoreTextView;
    int index;
    int question;
    int score;
    ProgressBar progressBar;



    private TrueFalse[] mQuestionBank = new TrueFalse[] {
            new TrueFalse(R.string.question_1, true),
            new TrueFalse(R.string.question_2, true),
            new TrueFalse(R.string.question_3, true),
            new TrueFalse(R.string.question_4, true),
            new TrueFalse(R.string.question_5, true),
            new TrueFalse(R.string.question_6, false),
            new TrueFalse(R.string.question_7, true),
            new TrueFalse(R.string.question_8, false),
            new TrueFalse(R.string.question_9, true),
            new TrueFalse(R.string.question_10, true),
            new TrueFalse(R.string.question_11, false),
            new TrueFalse(R.string.question_12, false),
            new TrueFalse(R.string.question_13,true)
    };

    final int PROGRESS_BAR_INCREMENT = (int) Math.ceil(100.0 / mQuestionBank.length);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState != null){

            score = savedInstanceState.getInt("ScoreKey");
            index = savedInstanceState.getInt("IndexKey");

        }else{

            score = 0;
            index = 0;

        }

        trueButton = (Button)findViewById(R.id.true_button);
        falseButton = (Button)findViewById(R.id.false_button);
        questionTextView = (TextView)findViewById(R.id.question_text_view);
        scoreTextView = (TextView)findViewById(R.id.score);
        progressBar = (ProgressBar)findViewById(R.id.progress_bar);



         question = mQuestionBank[index].getQuestionID();
        questionTextView.setText(question);
        scoreTextView.setText("Score " + score + "/" + mQuestionBank.length);


        trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CheckAnswer(true);
                updateQuestion();

            }
        });

        falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CheckAnswer(false);
                updateQuestion();
            }
        });


    }

    public void updateQuestion(){

        index = (index + 1) % mQuestionBank.length;

        if(index == 0){
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Game Over!");
            alert.setCancelable(false);
            alert.setMessage("You Scored " + score + " Points!");
            alert.setPositiveButton("Play Again!", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {


                    index = 1;
                    score = 0;
                    scoreTextView.setText("Score " + "0" + "/" + mQuestionBank.length);
                    progressBar.setProgress(0);

                }
            });
            alert.show();
        }

        question = mQuestionBank[index].getQuestionID();
        questionTextView.setText(question);
        progressBar.incrementProgressBy(PROGRESS_BAR_INCREMENT);
        scoreTextView.setText("Score " + score + "/" + mQuestionBank.length);

    }

    private void CheckAnswer(boolean userSelection){
        
        boolean correctAnswer = mQuestionBank[index].isAnswer();

        if(userSelection == correctAnswer){
            Toast.makeText(this, "You got it!", Toast.LENGTH_SHORT).show();
            score++;
        }else{
            Toast.makeText(this, "Wrong Answer", Toast.LENGTH_SHORT).show();
        }
        
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("ScoreKey" , score);
        outState.putInt("IndexKey" , index);

    }
}
