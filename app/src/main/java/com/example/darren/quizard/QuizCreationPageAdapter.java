package com.example.darren.quizard;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;

public class QuizCreationPageAdapter extends FragmentStatePagerAdapter {
    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    private List<QuestionBuilderFragment> mQuestions;
    private Context mContext;

    public QuizCreationPageAdapter(final Context context, FragmentManager fm) {
        super(fm);
        this.mQuestions = new LinkedList<>();
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment result;
        if (position == mQuestions.size()) {
            NewQuestionFragment fragment = new NewQuestionFragment();
            fragment.setOnDecisionListener(new NewQuestionFragment.OnDecisionListener() {
                @Override
                public void addQuestion() {
                    mQuestions.add(new QuestionBuilderFragment());
                    notifyDataSetChanged();
                }

                @Override
                public void publishQuiz() {
                    Toast.makeText(mContext, "FEATURE NOT IMPLEMENTED!", Toast.LENGTH_LONG).show();
                }
            });
            result = fragment;
        } else {
            result = mQuestions.get(position);
        }
        return result;
    }

    @Override
    public int getCount() {
        return mQuestions.size() + 1;
    }
}
