package cmsc355.groupF.quizard;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseError;

import java.util.ArrayList;
import java.util.List;

import cmsc355.groupF.quizard.quiz.Quiz;
import cmsc355.groupF.quizard.quiz.QuizUtils;


public class FindQuizActivity extends AppCompatActivity {

    public static final String QUIZ_INDEX = "QUIZ_INDEX";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_quiz);

        final ListView listView = findViewById(R.id.ListView);
        QuizUtils.getAllQuizzes(new QuizUtils.QuizQueryCallback() {
            @Override
            public void onLoad(List<Quiz> quizzes) {
                ArrayList<String> quizNames = new ArrayList<>(quizzes.size());
                for (Quiz quiz : quizzes) {
                    quizNames.add(quiz.getTitle());
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(),
                        android.R.layout.simple_dropdown_item_1line, quizNames);
                listView.setAdapter(adapter);
            }

            @Override
            public void onError(DatabaseError err) {
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), TakeQuizActivity.class);
                intent.putExtra(QUIZ_INDEX, position);
                startActivity(intent);
            }
        });
    }


    public void onClickSearch(View view) {
        //This is just to test the toast
        //Will be changed later (see notes at top)
        Toast.makeText(getApplicationContext(), "Quiz not found", Toast.LENGTH_SHORT).show();
    }
}

