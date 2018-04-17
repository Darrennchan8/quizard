package cmsc355.groupF.quizard;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DatabaseError;

import java.util.List;

import cmsc355.groupF.quizard.quiz.Question;
import cmsc355.groupF.quizard.quiz.Quiz;
import cmsc355.groupF.quizard.quiz.QuizUtils;


/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionViewFragment extends Fragment {

    private Question mQuestion;
    private int mIndex;

    public QuestionViewFragment() {
        mQuestion = new Question();
        mIndex = 1;
    }

    //ANDROID STUDIO WARNS THAT THIS CONSTRUCTOR SHOULD NOT BE USED
    public QuestionViewFragment(Question question, int index) {
        mQuestion = question;
        mIndex = index;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_question_view, container, false);
    }

    @Override
    @SuppressWarnings("ConstantConditions")
    public void onStart() {
        super.onStart();
        QuizUtils.getAllQuizzes(new QuizUtils.QuizQueryCallback() {
            @Override
            public void onLoad(List<Quiz> quizzes) {
                Quiz current = quizzes.get(0);
                fragmentBuild(current, 1);
            }

            @Override
            public void onError(DatabaseError err) {

            }
        });
    }

    public void fragmentBuild(Quiz quiz, int index) {
        List<Question> currentQs = quiz.getQuestions();
        String currentQuestion = currentQs.get(0).getQuestionText();
        TextView questionText = getView().findViewById(R.id.question_text);
        questionText.setText(currentQuestion);
        TextView questionNum = getView().findViewById(R.id.question_num);
        questionNum.setText(Integer.toString(index));
        TextView questionTotal = getView().findViewById(R.id.num_questions);
        questionTotal.setText(Integer.toString(currentQs.size()));

    }

}
