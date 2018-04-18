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

    public QuestionViewFragment() {
        this(new Question());
    }

    //ANDROID STUDIO WARNS THAT THIS CONSTRUCTOR SHOULD NOT BE USED
    public QuestionViewFragment(Question question) {
        mQuestion = question;
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
        fragmentBuild(mQuestion);
    }

    public void fragmentBuild(Question question) {
//        List<Question> currentQs = quiz.getQuestions();
//        String currentQuestion = currentQs.get(0).getQuestionText();
        TextView questionText = getView().findViewById(R.id.question_text);
        questionText.setText(question.getQuestionText());

    }

}
