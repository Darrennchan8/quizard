package cmsc355.groupF.quizard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DatabaseError;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cmsc355.groupF.quizard.quiz.Quiz;
import cmsc355.groupF.quizard.quiz.QuizUtils;

//User will be able to search through list of available quizzes to select the correct one
//1. text box
//2. drop-down, auto updating list
//3. button
// firebase

/*
TO FIX:
-- make button do something
   -- go to next page if valid quiz
   -- (for invalid quiz name) display error message OR be disabled until valid quiz is entered
-- no new line in text box
 */


public class FindQuizActivity extends AppCompatActivity {

    ArrayAdapter<String> adapter;
    AutoCompleteTextView textView;
    ArrayList<String> QUIZZES = new ArrayList<String>();
    Button searchQuizBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_quiz);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, QUIZZES);
        textView = findViewById(R.id.autoCompleteTextView);
        textView.setAdapter(adapter);
        textView.setThreshold(0);


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
    }

    public void onClickSearch(View view) {
        //This is just to test the toast
        //Will be changed later (see notes at top)
        Toast.makeText(getApplicationContext(), "Quiz not found", Toast.LENGTH_SHORT).show();
    }


    }

