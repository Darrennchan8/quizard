package com.example.darren.quizard;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.darren.quizard.Quiz.Quiz;


/**
 * A simple {@link Fragment} subclass.
 */
public class QuizOptionsFragment extends Fragment {

    private Quiz mOptions;

    public QuizOptionsFragment() {
        this.mOptions = new Quiz();
    }

    public Quiz getCurrentState() {
        Quiz currentState = this.mOptions;
        if (getView() != null) {
            currentState = new Quiz();
            EditText quizName = getView().findViewById(R.id.input_quiz_name);
            EditText quizPassword = getView().findViewById(R.id.quiz_password);
            currentState.setTitle(quizName.getText().toString());
            currentState.setPassword(quizPassword.getText().toString());
        }
        return currentState;
    }

    public void setCurrentState(Quiz options) {
        if (getView() != null && options != null) {
            EditText quizName = getView().findViewById(R.id.input_quiz_name);
            EditText quizPassword = getView().findViewById(R.id.quiz_password);
            quizName.setText(options.getTitle());
            quizPassword.setText(options.getPassword());
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        this.restoreState();
    }

    @Override
    public void onPause() {
        super.onPause();
        this.saveState();
    }

    public void saveState() {
        this.mOptions = getCurrentState();
    }

    public void restoreState() {
        this.setCurrentState(this.mOptions);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quiz_options, container, false);
    }

    @Override
    @SuppressWarnings("ConstantConditions")
    public void onStart() {
        super.onStart();
//        ActionBar bar = getSupportActionBar();
        ActionBar bar = null;
        final EditText quizName;
        final EditText quizPassword = getView().findViewById(R.id.quiz_password);
        final EditText startDate = getView().findViewById(R.id.quiz_start_date);
        final EditText startTime = getView().findViewById(R.id.quiz_start_time);
        final EditText endDate = getView().findViewById(R.id.quiz_end_date);
        final EditText endTime = getView().findViewById(R.id.quiz_end_time);
        if (bar == null) {
            Log.e(getClass().getName(), "getActionBar() returned null.");
            quizName = getView().findViewById(R.id.input_quiz_name);
            quizName.setVisibility(View.VISIBLE);
        } else {
            bar.setCustomView(R.layout.editable_actionbar);
            bar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
            quizName = (EditText) bar.getCustomView();
        }
//        startDate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.e(getClass().getName(), "Start date field clicked!");
//                final DatePicker picker = new DatePicker(CreateQuizActivity.this);
//                new AlertDialog.Builder(CreateQuizActivity.this)
//                        .setView(picker)
//                        .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) { dialog.cancel(); }
//                        })
//                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                Log.e(getClass().getName(), "OK clicked!");
//                            }
//                        })
//                        .show();
//            }
//        });
//        FloatingActionButton btnNewQuestion = getView().findViewById(R.id.new_question);
    }
}
