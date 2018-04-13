package cmsc355.groupF.quizard;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseError;

import java.util.List;

import cmsc355.groupF.quizard.quiz.Quiz;
import cmsc355.groupF.quizard.quiz.QuizUtils;

public class TakeQuizActivity extends AppCompatActivity {

    Button beginQuiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_quiz);
    }

    @Override
    protected void onStart(){
        super.onStart();
        QuizUtils.getAllQuizzes(new QuizUtils.QuizQueryCallback() {
            @Override
            public void onLoad(List<Quiz> quizzes) {
                Quiz current = quizzes.get(0);
                String currentTitle = current.getTitle();
                Log.i("TakeQuizActivity", currentTitle);
                TextView quizName = findViewById(R.id.quiz_name);
                quizName.setText(currentTitle);
            }

            @Override
            public void onError(DatabaseError err) {

            }
        });

        beginQuiz = findViewById(R.id.begin_quiz);
    }

    public void beginQuiz(View v) {
        Intent i = new Intent(this, QuestionView.class);
        startActivity(i);
    }
}
