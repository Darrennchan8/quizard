package cmsc355.groupF.quizard;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseError;

import java.util.List;

import cmsc355.groupF.quizard.quiz.Quiz;
import cmsc355.groupF.quizard.quiz.QuizUtils;

public class TakeQuizActivity extends AppCompatActivity {

    int quizIndex = 0;
    String quizPass = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_quiz);
        quizIndex = getIntent().getIntExtra(FindQuizActivity.QUIZ_INDEX, 0);
    }

    @Override
    protected void onStart(){
        super.onStart();
        QuizUtils.getAllQuizzes(new QuizUtils.QuizQueryCallback() {
            @Override
            public void onLoad(List<Quiz> quizzes) {
                TextView quizName = findViewById(R.id.quiz_name);
                Quiz current = quizzes.get(quizIndex);
                quizName.setText(current.getTitle());
                quizPass = current.getPassword();
            }

            @Override
            public void onError(DatabaseError err) {
            }
        });

        final EditText password = findViewById(R.id.quiz_password);
        Button beginQuiz = findViewById(R.id.begin_quiz);
        beginQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                password.requestFocus();
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(password.getWindowToken(), 0);
                }
                if(password.getText().toString().equals(quizPass)) {
                    Intent i = new Intent(getApplicationContext(), QuestionViewActivity.class);
                    i.putExtra(FindQuizActivity.QUIZ_INDEX, quizIndex);
                    startActivity(i);
                } else {
                    password.setText("");
                    Snackbar.make(findViewById(R.id.root), R.string.incorrect_password, Snackbar.LENGTH_LONG).show();
                }
            }
        });
    }
}
