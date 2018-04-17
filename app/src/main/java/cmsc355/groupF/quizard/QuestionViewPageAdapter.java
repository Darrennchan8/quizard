package cmsc355.groupF.quizard;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

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
        this.mQuestions = new LinkedList<>();
        this.setOnSubmitListener(null);
    }

    public void setOnSubmitListener(OnSubmitListener listener) {
        this.mListener = listener;
    }

    public void populateQuiz(Quiz current) {
        for (int i = 0; i < current.getQuestions().size(); i++) {
            mQuestions.add(new QuestionViewFragment(current.getQuestions().get(i),i));
        }
    }

    @Override
    public Fragment getItem(int position) {
        position--;
        if (position == -1) {
            return this.mQuestions.get(0);
        } else {
            return mQuestions.get(position);
        }
    }

    @Override
    public int getCount() {
        return mQuestions.size();
    }
}
