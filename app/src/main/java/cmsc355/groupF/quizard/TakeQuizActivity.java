package cmsc355.groupF.quizard;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseError;

import java.util.List;

import cmsc355.groupF.quizard.quiz.Quiz;
import cmsc355.groupF.quizard.quiz.QuizUtils;

public class TakeQuizActivity extends AppCompatActivity {

    int quizIndex = 0;

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
            }

            @Override
            public void onError(DatabaseError err) {
            }
        });

        Button beginQuiz = findViewById(R.id.begin_quiz);
        beginQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), QuestionViewActivity.class);
                i.putExtra(FindQuizActivity.QUIZ_INDEX, quizIndex);
                startActivity(i);
            }
        });
    }
}
