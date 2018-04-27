package cmsc355.groupF.quizard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseError;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cmsc355.groupF.quizard.quiz.Quiz;
import cmsc355.groupF.quizard.quiz.QuizUtils;


public class FindQuizActivity extends AppCompatActivity {

    ArrayAdapter<String> adapter;
    ListView listView;
    ArrayList<String> QUIZ_NAMES = new ArrayList<String>();
    ArrayList<Quiz> QUIZZES = new ArrayList<Quiz>();
    HashMap<Quiz,Integer> map = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_quiz);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, QUIZ_NAMES);
        listView = findViewById(R.id.ListView);
        listView.setAdapter(adapter);


        QuizUtils.getAllQuizzes(new QuizUtils.QuizQueryCallback() {
            @Override
            public void onLoad(List<Quiz> quizzes) {
                QUIZZES.clear();
                ArrayList<String> temp = new ArrayList<>(quizzes.size());
                for(int i=0; i<quizzes.size(); i++) {
                    temp.add(quizzes.get(i).getTitle());
                    QUIZZES.add(quizzes.get(i));
                    map.put(quizzes.get(i), i);
                }
                QUIZ_NAMES.clear();
                QUIZ_NAMES.addAll(temp);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(DatabaseError err) {
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Quiz quiz = QUIZZES.get(position);
                Intent intent = new Intent(FindQuizActivity.this, TakeQuizActivity.class);
                intent.putExtra("INDEX_OF_QUIZ_IN_DATABASE", map.get(quiz));
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

