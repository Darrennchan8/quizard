package com.example.darren.quizard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class TakeQuizActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_quiz);
    }

    public void beginQuiz(View v) {

        Intent i;
        i = new Intent(TakeQuizActivity.this, QuestionView.class);
        startActivity(i);
    }
}
