package cmsc355.groupF.quizard;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import cmsc355.groupF.quizard.quiz.Question;
import cmsc355.groupF.quizard.quiz.Quiz;

public class QuestionViewPageAdapter extends FragmentStatePagerAdapter {
    public interface OnSubmitListener {
        void onSubmit(Quiz quiz);
    }
    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }

    private List<QuestionViewFragment> mQuestions;
    private OnSubmitListener mListener;

    public QuestionViewPageAdapter(FragmentManager fm) {
        super(fm);
        this.mQuestions = new ArrayList<>();
        this.setOnSubmitListener(null);
    }

    public void setOnSubmitListener(OnSubmitListener listener) {
        this.mListener = listener;
    }

    public void populateQuiz(Quiz current) {
        for (Question quizQuestion : current.getQuestions()) {
            mQuestions.add(new QuestionViewFragment(quizQuestion));
        }
    }

    @Override
    public Fragment getItem(int position) {
        return mQuestions.get(position);
    }

    @Override
    public int getCount() {
        return mQuestions.size();
    }
}
