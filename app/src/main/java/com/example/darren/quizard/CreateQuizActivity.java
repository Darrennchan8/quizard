package com.example.darren.quizard;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;

import com.example.darren.quizard.quiz.Quiz;
import com.example.darren.quizard.quiz.QuizUtils;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

public class CreateQuizActivity extends AppCompatActivity {

    public static class Status {
        public static final int PUBLISHED = 1;
    }

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
                final Snackbar loadingBar = Snackbar.make(viewPager, R.string.publishing_quiz, Snackbar.LENGTH_INDEFINITE);
                loadingBar.show();
                QuizUtils.publish(quiz, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                        loadingBar.dismiss();
                        if (databaseError == null) {
                            setResult(Status.PUBLISHED);
                            finish();
                        } else {
                            Log.e(getClass().getName(), databaseError.toString());
                            Snackbar.make(viewPager, R.string.error_publishing_quiz, Snackbar.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }
}
