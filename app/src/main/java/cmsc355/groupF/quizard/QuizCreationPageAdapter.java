package cmsc355.groupF.quizard;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.LinkedList;
import java.util.List;

import cmsc355.groupF.quizard.quiz.Quiz;

public class QuizCreationPageAdapter extends FragmentStatePagerAdapter implements ViewPagerBuilder.RobustAdapter {

    public interface OnSubmitListener {
        void onSubmit(Quiz quiz);
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }

    private QuizOptionsFragment mOptionsFragment;
    private List<QuestionBuilderFragment> mQuestions;
    private OnSubmitListener mListener;

    public QuizCreationPageAdapter(FragmentManager fm) {
        super(fm);
        this.mOptionsFragment = new QuizOptionsFragment();
        this.mQuestions = new LinkedList<>();
        this.setOnSubmitListener(null);
    }

    private NewQuestionFragment newQuestionFragment() {
        NewQuestionFragment fragment = new NewQuestionFragment();
        fragment.setOnDecisionListener(new NewQuestionFragment.OnDecisionListener() {
            @Override
            public void addQuestion() {
                mQuestions.add(new QuestionBuilderFragment());
                notifyDataSetChanged();
            }

            @Override
            public void publishQuiz() {
                mListener.onSubmit(generateQuiz());
            }
        });
        return fragment;
    }

    public void setOnSubmitListener(OnSubmitListener listener) {
        this.mListener = listener;
    }

    public Quiz generateQuiz() {
        Quiz currQuiz = this.mOptionsFragment.getCurrentState();
        for (QuestionBuilderFragment question : this.mQuestions) {
            currQuiz.addQuestion(question.getCurrentState());
        }
        return currQuiz;
    }

    @Override
    public Fragment getItem(int position) {
        position--;
        if (position == -1) {
            return this.mOptionsFragment;
        } else if (position == mQuestions.size()) {
            return newQuestionFragment();
        } else {
            return mQuestions.get(position);
        }
    }

    @Override
    public int getCount() {
        return mQuestions.size() + 2;
    }
}
