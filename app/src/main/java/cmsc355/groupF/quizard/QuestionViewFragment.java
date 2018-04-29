package cmsc355.groupF.quizard;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import cmsc355.groupF.quizard.quiz.MultipleChoiceAnswer;
import cmsc355.groupF.quizard.quiz.Question;


/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionViewFragment extends Fragment {

    private Question mQuestion;

    public QuestionViewFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_question_view, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    public void useQuestion(Question question) {
        this.mQuestion = question;
    }

    @Override
    public void onPause() {
        super.onPause();
        if (this.mQuestion != null && getView() != null) {
            EditText shortResponseField = getView().findViewById(R.id.answer_short_response);
            this.mQuestion.setStudentShortAnswer(shortResponseField.getText().toString());
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (this.mQuestion != null && getView() != null) {
            TextView questionText = getView().findViewById(R.id.question_text);
            ScrollView multipleChoiceSection = getView().findViewById(R.id.multiple_choice_wrapper);
            final RadioGroup multipleChoiceField = multipleChoiceSection.findViewById(R.id.answer_multiple_choice);
            EditText shortResponseField = getView().findViewById(R.id.answer_short_response);

            questionText.setText(this.mQuestion.getQuestionText());
            multipleChoiceField.removeAllViews();
            shortResponseField.getText().clear();
            shortResponseField.setVisibility(View.GONE);
            multipleChoiceSection.setVisibility(View.GONE);

            switch (this.mQuestion.getQuestionType()) {
                case MULTIPLE_CHOICE:
                    multipleChoiceSection.setVisibility(View.VISIBLE);
                    for (MultipleChoiceAnswer answer : this.mQuestion.getMultipleChoiceAnswers()) {
                        RadioButton answerRadio = (RadioButton) LayoutInflater.from(getContext())
                                .inflate(R.layout.multiple_choice_answer, multipleChoiceField, false);
                        answerRadio.setText(answer.getText());
                        answerRadio.setChecked(answer.getStudentChoice());
                        answerRadio.setId(multipleChoiceField.getChildCount());
                        multipleChoiceField.addView(answerRadio);
                    }
                    multipleChoiceField.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                        Integer previousCheckedIndex = null;

                        @Override
                        public void onCheckedChanged(RadioGroup group, int checkedId) {
                            if (previousCheckedIndex != null) {
                                mQuestion.getMultipleChoiceAnswers().get(previousCheckedIndex).setStudentChoice(false);
                            }
                            mQuestion.getMultipleChoiceAnswers().get(checkedId).setStudentChoice(true);
                        }
                    });
                    break;
                case SHORT_ANSWER:
                    shortResponseField.setVisibility(View.VISIBLE);
                    shortResponseField.setText(mQuestion.getStudentShortAnswer());
                    break;
            }
        }
    }
}
