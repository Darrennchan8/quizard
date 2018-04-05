package com.example.darren.quizard;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;

import com.example.darren.quizard.quiz.MultipleChoiceAnswer;
import com.example.darren.quizard.quiz.Question;
import com.example.darren.quizard.quiz.QuestionType;


/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionBuilderFragment extends Fragment {


    private Question mQuestion;

    @Override
    public void onPause() {
        super.onPause();
        this.saveState();
    }

    public QuestionBuilderFragment() {
        this.mQuestion = new Question();
    }

    public Question getCurrentState() {
        Question currentState = this.mQuestion;
        View v = getView();
        if (v != null) {
            currentState = new Question();
            EditText questionName = v.findViewById(R.id.question_name);
            Spinner questionType = v.findViewById(R.id.question_type_spinner);
            LinearLayout multipleChoiceAnswers = v.findViewById(R.id.multiple_choice_list);
            EditText shortResponseAnswer = v.findViewById(R.id.short_response_answer);
            currentState.setQuestionText(questionName.getText().toString());
            switch (questionType.getSelectedItemPosition()) {
                case 0:
                    currentState.setQuestionType(QuestionType.MULTIPLE_CHOICE);
                    for (int i = 0; i != multipleChoiceAnswers.getChildCount() - 1; i++) {
                        CheckBox box = multipleChoiceAnswers.getChildAt(i)
                                .findViewById(R.id.correctness_box);
                        EditText answer = multipleChoiceAnswers.getChildAt(i)
                                .findViewById(R.id.answer_choice);
                        currentState.addMultipleChoiceAnswer(new MultipleChoiceAnswer(answer.getText().toString(),
                                box.isChecked()));
                    }
                    break;
                case 1:
                    currentState.setQuestionType(QuestionType.SHORT_ANSWER);
                    currentState.setShortAnswer(shortResponseAnswer.getText().toString());
                    break;
            }
        }
        return currentState;
    }

    public void setCurrentState(Question question) {
        View v = getView();
        if (question != null && v != null) {
            EditText questionName = v.findViewById(R.id.question_name);
            Spinner questionType = v.findViewById(R.id.question_type_spinner);
            LinearLayout multipleChoiceAnswers = v.findViewById(R.id.multiple_choice_list);
            EditText shortResponseAnswer = v.findViewById(R.id.short_response_answer);
            questionName.setText(question.getQuestionText());
            switch (question.getQuestionType()) {
                case MULTIPLE_CHOICE:
                    questionType.setSelection(0, true);
                    while (multipleChoiceAnswers.getChildCount() > 1) {
                        multipleChoiceAnswers.removeViewAt(0);
                    }
                    for (MultipleChoiceAnswer answer : question.getMultipleChoiceAnswers()) {
                        View answerChoice = LayoutInflater.from(getContext()).inflate(
                                R.layout.multiple_choice_item, multipleChoiceAnswers, false);
                        CheckBox box = answerChoice.findViewById(R.id.correctness_box);
                        EditText answerChoiceText = answerChoice.findViewById(R.id.answer_choice);
                        box.setChecked(answer.isCorrect());
                        answerChoiceText.setText(answer.getText());
                        multipleChoiceAnswers.addView(answerChoice, multipleChoiceAnswers.getChildCount() - 1);
                    }
                    break;
                case SHORT_ANSWER:
                    questionType.setSelection(1, true);
                    shortResponseAnswer.setText(question.getShortAnswer());
                    break;
            }
        }
    }

    public void saveState() {
        this.mQuestion = getCurrentState();
    }

    public void restoreState() {
        this.setCurrentState(this.mQuestion);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_question_builder, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        this.restoreState();
    }

    @Override
    @SuppressWarnings("ConstantConditions")
    public void onStart() {
        super.onStart();
        Spinner spinner = getView().findViewById(R.id.question_type_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                getContext(), R.array.question_types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        final ScrollView multiChoiceSection = getView().findViewById(R.id.multiple_choice_section);
        final LinearLayout multiChoiceLayout = getView().findViewById(R.id.multiple_choice_list);
        final ConstraintLayout newQuestionAnswer = getView().findViewById(R.id.new_multiple_choice_item);
        newQuestionAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ConstraintLayout answer = (ConstraintLayout)LayoutInflater.from(getContext())
                        .inflate(R.layout.multiple_choice_item, multiChoiceLayout, false);
                multiChoiceLayout.addView(answer, multiChoiceLayout.getChildCount() - 1);
                final ImageView btnDel = answer.findViewById(R.id.delete_btn);
                final EditText inputAnswer = answer.findViewById(R.id.answer_choice);
                btnDel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        multiChoiceLayout.removeView(answer);
                    }
                });
                inputAnswer.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        btnDel.setVisibility(hasFocus ? View.VISIBLE : View.GONE);
                    }
                });
            }
        });
        final ConstraintLayout shortResponseSection = getView().findViewById(R.id.short_response_section);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        multiChoiceSection.setVisibility(View.VISIBLE);
                        shortResponseSection.setVisibility(View.GONE);
                        break;
                    case 1:
                        multiChoiceSection.setVisibility(View.GONE);
                        shortResponseSection.setVisibility(View.VISIBLE);
                        break;
                    default:
                        multiChoiceSection.setVisibility(View.GONE);
                        shortResponseSection.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                multiChoiceSection.setVisibility(View.GONE);
                shortResponseSection.setVisibility(View.GONE);
            }
        });
    }
}
