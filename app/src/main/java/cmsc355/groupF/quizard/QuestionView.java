package cmsc355.groupF.quizard;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import java.util.List;

import cmsc355.groupF.quizard.quiz.Quiz;
import cmsc355.groupF.quizard.quiz.QuizUtils;

public class QuestionView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_view);
        final QuestionViewPageAdapter questionViewPageAdapter = new QuestionViewPageAdapter(getSupportFragmentManager());
        final ViewPager viewPager = findViewById(R.id.root_view);
        QuizUtils.getAllQuizzes(new QuizUtils.QuizQueryCallback() {
            @Override
            public void onLoad(List<Quiz> quizzes) {
                Quiz current = quizzes.get(0);
                questionViewPageAdapter.populateQuiz(current);
                viewPager.setAdapter(questionViewPageAdapter);
            }

            @Override
            public void onError(DatabaseError err) {

            }
        });


        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            private int currPage = 0;

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                questionViewPageAdapter.getItem(currPage).onPause();
                questionViewPageAdapter.getItem(position).onResume();
                currPage = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == ViewPager.SCROLL_STATE_IDLE) {
                    viewPager.requestFocus();
                    InputMethodManager Ime = ((InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE));
                    if (Ime != null) {
                        Ime.hideSoftInputFromWindow(viewPager.getWindowToken(), 0);
                    }
                }
            }
        });

//BELOW HERE IS FOR WHEN SUBMITTING THE QUIZ ANSWERS I THINK
//        questionViewPageAdapter.setOnSubmitListener(new QuestionViewPageAdapter.OnSubmitListener() {
//            @Override
//            public void onSubmit(Quiz quiz) {
//                final Snackbar loadingBar = Snackbar.make(viewPager, R.string.publishing_quiz, Snackbar.LENGTH_INDEFINITE);
//                loadingBar.show();
//                QuizUtils.publish(quiz, new DatabaseReference.CompletionListener() {
//                    @Override
//                    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
//                        loadingBar.dismiss();
//                        if (databaseError == null) {
//                            setResult(CreateQuizActivity.Status.PUBLISHED);
//                            finish();
//                        } else {
//                            Log.e(getClass().getName(), databaseError.toString());
//                            Snackbar.make(viewPager, R.string.error_publishing_quiz, Snackbar.LENGTH_LONG).show();
//                        }
//                    }
//                });
//            }
//        });
    }
}
