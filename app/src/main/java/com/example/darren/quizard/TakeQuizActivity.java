package com.example.darren.quizard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONException;
import org.json.JSONObject;

public class TakeQuizActivity extends AppCompatActivity {

    TextView quizName;
    Button beginQuiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_quiz);
    }

    @Override
    protected void onStart(){
        super.onStart();
        JSONObject mockQuiz = null;
        try {
            mockQuiz = new JSONObject(getString(R.string.mock_quiz));
            quizName = findViewById(R.id.quiz_name);
            String name = mockQuiz.getJSONObject("quiz").getString("quiz_name");
            quizName.setText(name);
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e(getClass().getName(), "Could not read JSON", e);
            throw new RuntimeException(e);
        }
        beginQuiz = (Button) findViewById(R.id.begin_quiz);
    }

    public void beginQuiz(View v) {
        Intent i;
        i = new Intent(TakeQuizActivity.this, QuestionView.class);
        startActivity(i);
    }
}
