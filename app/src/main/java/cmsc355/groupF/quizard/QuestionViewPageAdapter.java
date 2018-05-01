package cmsc355.groupF.quizard;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import java.util.List;

import cmsc355.groupF.quizard.quiz.Question;
import cmsc355.groupF.quizard.quiz.Quiz;

public class QuestionViewPageAdapter extends FragmentStatePagerAdapter implements ViewPagerBuilder.RobustAdapter {

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }

    private List<Question> mQuestions;
    private QuestionViewFragment[] mQuestionViews;
    private SubmitQuizFragment mSubmitFragment;
    private SubmitQuizFragment.QuizSubmitListener mListener;

    public QuestionViewPageAdapter(FragmentManager fm, Quiz quiz) {
        super(fm);
        this.mQuestions = quiz.getQuestions();
        this.mQuestionViews = new QuestionViewFragment[quiz.getQuestions().size()];
        this.mSubmitFragment = new SubmitQuizFragment();
        this.mSubmitFragment.setOnQuizSubmitListener(new SubmitQuizFragment.QuizSubmitListener() {
            @Override
            public void onSubmit() {
                if (mListener != null) {
                    mListener.onSubmit();
                }
            }
        });
    }

    @Override
    public Fragment getItem(int position) {
        if (position < mQuestionViews.length) {
            if (mQuestionViews[position] == null) {
                mQuestionViews[position] = new QuestionViewFragment();
                mQuestionViews[position].useQuestion(mQuestions.get(position));
            }
            return mQuestionViews[position];
        } else {
            return mSubmitFragment;
        }
    }

    public void setOnQuizSubmitListener(SubmitQuizFragment.QuizSubmitListener listener) {
        this.mListener = listener;
    }

    @Override
    public int getCount() {
        return mQuestionViews.length + 1;
    }
}
