package cmsc355.groupF.quizard;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

import cmsc355.groupF.quizard.quiz.Question;
import cmsc355.groupF.quizard.quiz.Quiz;

public class QuestionViewPageAdapter extends FragmentStatePagerAdapter {
    private List<Question> mQuestions;
    private QuestionViewFragment[] mQuestionViews;

    public QuestionViewPageAdapter(FragmentManager fm, Quiz quiz) {
        super(fm);
        this.mQuestions = quiz.getQuestions();
        this.mQuestionViews = new QuestionViewFragment[quiz.getQuestions().size()];
    }

    @Override
    public Fragment getItem(int position) {
        if (mQuestionViews[position] == null) {
            mQuestionViews[position] = new QuestionViewFragment();
            mQuestionViews[position].useQuestion(mQuestions.get(position));
        }
        return mQuestionViews[position];
    }

    @Override
    public int getCount() {
        return mQuestionViews.length;
    }
}
