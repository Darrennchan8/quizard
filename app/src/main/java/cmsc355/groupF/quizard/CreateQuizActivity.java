package cmsc355.groupF.quizard;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import cmsc355.groupF.quizard.quiz.Quiz;
import cmsc355.groupF.quizard.quiz.QuizUtils;

public class CreateQuizActivity extends AppCompatActivity {

    public static class Status {
        public static final int PUBLISHED = 1;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_quiz);
        final QuizCreationPageAdapter quizCreationPageAdapter = new QuizCreationPageAdapter(getSupportFragmentManager());
        final ViewPager viewPager =
                new ViewPagerBuilder(this)
                        .setPageChangeHook(new ViewPagerBuilder.PageChangeHook() {
                            @Override
                            public String getAppBarText(int position, int total) {
                                return position == 1 || position == total ? getString(R.string.create_quiz)
                                        : getString(R.string.question_i_of_n, position - 1, total - 2);
                            }
                        })
                        .build(R.id.root_view, quizCreationPageAdapter);
        quizCreationPageAdapter.setOnSubmitListener(new QuizCreationPageAdapter.OnSubmitListener() {
            @Override
            public void onSubmit(Quiz quiz) {
                final Snackbar loadingBar = Snackbar.make(viewPager, R.string.publishing_quiz, Snackbar.LENGTH_INDEFINITE);
                loadingBar.show();
                QuizUtils.publish(quiz, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                        loadingBar.dismiss();
                        if (databaseError == null) {
                            setResult(Status.PUBLISHED);
                            finish();
                        } else {
                            Log.e(getClass().getName(), databaseError.toString());
                            Snackbar.make(viewPager, R.string.error_publishing_quiz, Snackbar.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }
}
