package cmsc355.groupF.quizard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DatabaseError;

import java.util.ArrayList;
import java.util.List;

import cmsc355.groupF.quizard.quiz.Quiz;
import cmsc355.groupF.quizard.quiz.QuizUtils;


public class FindQuizActivity extends AppCompatActivity {

    ArrayAdapter<String> adapter;
    AutoCompleteTextView autoCompleteTextView;
    ArrayList<String> QUIZZES = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_quiz);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, QUIZZES);
        autoCompleteTextView = findViewById(R.id.autoCompleteTextView);
        autoCompleteTextView.setAdapter(adapter);
        autoCompleteTextView.setThreshold(0);


        QuizUtils.getAllQuizzes(new QuizUtils.QuizQueryCallback() {
            @Override
            public void onLoad(List<Quiz> quizzes) {
                ArrayList<String> temp = new ArrayList<>(quizzes.size());
                for(int i=0; i<quizzes.size(); i++) {
                    temp.add(quizzes.get(i).getTitle());
                }
                QUIZZES.clear();
                QUIZZES.addAll(temp);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(DatabaseError err) {
            }
        });

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(FindQuizActivity.this, TakeQuizActivity.class);
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

