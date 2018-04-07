package com.example.darren.quizard;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    public static final int TAKE_QUIZ_ACTIVITY = 1;
    public static final int CREATE_QUIZ_ACTIVITY = 2;

    private LinearLayout rootView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ImageButton studentBtn = findViewById(R.id.btn_student);
        final ImageButton teacherBtn = findViewById(R.id.btn_teacher);
        rootView = findViewById(R.id.root_view);
        studentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TakeQuizActivity.class);
                startActivityForResult(intent, TAKE_QUIZ_ACTIVITY);
            }
        });
        teacherBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CreateQuizActivity.class);
                startActivityForResult(intent, CREATE_QUIZ_ACTIVITY);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case TAKE_QUIZ_ACTIVITY:
                break;
            case CREATE_QUIZ_ACTIVITY:
                switch (resultCode) {
                    case CreateQuizActivity.Status.PUBLISHED:
                        Snackbar.make(rootView, R.string.quiz_published, Snackbar.LENGTH_LONG).show();
                        break;
                    default:
                        Snackbar.make(rootView, R.string.quiz_not_published, Snackbar.LENGTH_LONG).show();
                }
                break;
        }
    }
}
