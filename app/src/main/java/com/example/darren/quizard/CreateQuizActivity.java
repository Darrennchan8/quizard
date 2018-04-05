package com.example.darren.quizard;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;

import com.example.darren.quizard.Quiz.Quiz;

public class CreateQuizActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_quiz);
        final QuizCreationPageAdapter quizCreationPageAdapter = new QuizCreationPageAdapter(getSupportFragmentManager());
        final ViewPager viewPager = findViewById(R.id.root_view);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            private int currPage = 0;

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                quizCreationPageAdapter.getItem(currPage).onPause();
                quizCreationPageAdapter.getItem(position).onResume();
                currPage = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == ViewPager.SCROLL_STATE_IDLE) {
                    viewPager.requestFocus();
                    InputMethodManager Ime = ((InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE));
                    if (Ime != null) {
                        Ime.hideSoftInputFromWindow(viewPager.getWindowToken(), 0);
                    }
                }
            }
        });
        viewPager.setAdapter(quizCreationPageAdapter);
        quizCreationPageAdapter.setOnSubmitListener(new QuizCreationPageAdapter.OnSubmitListener() {
            @Override
            public void onSubmit(Quiz quiz) {
                Log.i(getClass().getName(), quiz.toString());
            }
        });
    }
}
