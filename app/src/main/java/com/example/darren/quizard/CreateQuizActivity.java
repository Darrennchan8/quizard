package com.example.darren.quizard;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class CreateQuizActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_quiz);
//        QuizOptionsFragment initialScreen = new QuizOptionsFragment();
//        getSupportFragmentManager().beginTransaction()
//                .add(R.id.root_view, initialScreen).commit();
//
//        QuestionBuilderFragment newQuestionFragment = new QuestionBuilderFragment();
//        getSupportFragmentManager().beginTransaction()
//                .replace(R.id.root_view, newQuestionFragment)
//                .addToBackStack(null)
//                .commit();
        final QuizCreationPageAdapter quizCreationPageAdapter =
                new QuizCreationPageAdapter(this, getSupportFragmentManager());
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
    }
}
