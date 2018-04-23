package cmsc355.groupF.quizard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.database.DatabaseError;

import java.util.List;

import cmsc355.groupF.quizard.quiz.Quiz;
import cmsc355.groupF.quizard.quiz.QuizUtils;

//User will be able to search through list of available quizzes to select the correct one
//1. text box
//2. drop-down, auto updating list
//3. button
// firebase

public class FindQuizActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_quiz);
        QuizUtils.getAllQuizzes(new QuizUtils.QuizQueryCallback() {
            @Override
            public void onLoad(List<Quiz> quizzes) {
                int i = 0;
            }

            @Override
            public void onError(DatabaseError err) {

            }
        });
    }
}
