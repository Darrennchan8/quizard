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

    public interface QuizSubmitListener {
        void quizSubmit(Quiz quiz);
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }

    private List<Question> mQuestions;
    private QuestionViewFragment[] mQuestionViews;
    private SubmitQuizFragment mSubmitFragment;
    private QuizSubmitListener mListener;

    public QuestionViewPageAdapter(FragmentManager fm, Quiz quiz) {
        super(fm);
        this.mQuestions = quiz.getQuestions();
        this.mQuestionViews = new QuestionViewFragment[quiz.getQuestions().size()];
        this.mSubmitFragment = new SubmitQuizFragment();
        this.setQuizSubmitListener(null);
    }

    private SubmitQuizFragment newSubmitQuizFragment() {
        SubmitQuizFragment fragment = new SubmitQuizFragment();
        fragment.setQuizSubmitListener(new SubmitQuizFragment.QuizSubmitListener() {

            @Override
            public void quizSubmit(Quiz quiz) {
                mListener.quizSubmit(quiz); //FILL IN WITH AN ACTUAL QUIZ (MAYBE)
            }
        });
        return fragment;
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

    public void setQuizSubmitListener(QuizSubmitListener listener) {
        this.mListener = listener;
    }

    @Override
    public int getCount() {
        return mQuestionViews.length + 1;
    }
}
