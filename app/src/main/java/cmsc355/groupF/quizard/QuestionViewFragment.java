package cmsc355.groupF.quizard;


import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseError;

import java.util.List;

import cmsc355.groupF.quizard.quiz.MultipleChoiceAnswer;
import cmsc355.groupF.quizard.quiz.Question;
import cmsc355.groupF.quizard.quiz.Quiz;
import cmsc355.groupF.quizard.quiz.QuizUtils;


/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionViewFragment extends Fragment {

    private Question mQuestion;
    private int mQuestionNum;

    public QuestionViewFragment() {
        this(new Question());
    }

    //ANDROID STUDIO WARNS THAT THIS CONSTRUCTOR SHOULD NOT BE USED
    public QuestionViewFragment(Question question) {
        mQuestion = question;
    }

    public void setmQuestionNum(int mQuestionNum) {
        this.mQuestionNum = mQuestionNum;
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

        final LinearLayout mAnswerList = getView().findViewById(R.id.answer_choice_list);
        final ScrollView mAnswers = getView().findViewById(R.id.multiple_choice_answer_section);
        final ConstraintLayout shortResponseAnswer = getView().findViewById(R.id.short_response_answer);

//NEED TO GET INDEX AND TOTAL FROM PAGEADAPTER SOMEHOW, position VARIABLE?
        TextView questionNum = getView().findViewById(R.id.question_num);
//        questionNum.setText(question.getQuestionText());
        TextView questionTotal = getView().findViewById(R.id.num_questions);
//        questionTotal.setText(question.getQuestionText());

        //set the question text for each question
        TextView questionText = getView().findViewById(R.id.question_text);
        questionText.setText(question.getQuestionText());

        switch(question.getQuestionType()){
            case MULTIPLE_CHOICE:
                mAnswers.setVisibility(View.VISIBLE);
                shortResponseAnswer.setVisibility(View.GONE);
                List<MultipleChoiceAnswer> answers = question.getMultipleChoiceAnswers();
                for(int i = 0; i < answers.size(); i++) {
                    //USE LAYOUT INFLATER HERE TO MAKE EACH NEW ANSWER OPTION
                    LayoutInflater inflater = LayoutInflater.from(getContext());
                    View answerChoice = inflater.inflate(R.layout.multiple_choice_answer, mAnswerList, false);
                    mAnswerList.addView(answerChoice, mAnswerList.getChildCount());
                    TextView answerText = answerChoice.findViewById(R.id.answer_option);
                    answerText.setText(answers.get(i).getText());
                }
                break;
            case SHORT_ANSWER:
                mAnswers.setVisibility(View.GONE);
                shortResponseAnswer.setVisibility(View.VISIBLE);
                break;
        }
    }
}
