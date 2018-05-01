package cmsc355.groupF.quizard;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.inputmethod.InputMethodManager;

import com.google.firebase.database.DatabaseError;

import java.util.List;

import cmsc355.groupF.quizard.quiz.Quiz;
import cmsc355.groupF.quizard.quiz.QuizUtils;

public class QuestionViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_view);

        final int quizIndex = getIntent().getIntExtra(FindQuizActivity.QUIZ_INDEX, 0);

        QuizUtils.getAllQuizzes(new QuizUtils.QuizQueryCallback() {
            @Override
            public void onLoad(List<Quiz> quizzes) {
                final Quiz targetQuiz = quizzes.get(quizIndex);
                final QuestionViewPageAdapter questionViewPageAdapter = new QuestionViewPageAdapter(getSupportFragmentManager(),targetQuiz);
                final ViewPager viewPager =
                        new ViewPagerBuilder(QuestionViewActivity.this).setPageChangeHook(new ViewPagerBuilder.PageChangeHook() {
                                    @Override
                                    public String getAppBarText(int position, int total) {
                                        if(position < total) {
                                            return getString(R.string.question_i_of_n, position, total - 1);
                                        } else {
                                            return targetQuiz.getTitle();
                                        }
                                    }
                                })
                                .build(R.id.root_view, questionViewPageAdapter);
                viewPager.setAdapter(questionViewPageAdapter);
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
                            InputMethodManager Ime = ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE));
                            if (Ime != null) {
                                Ime.hideSoftInputFromWindow(viewPager.getWindowToken(), 0);
                            }
                        }
                    }
                });
            }

            @Override
            public void onError(DatabaseError err) {
            }
        });

//        BELOW HERE IS FOR WHEN SUBMITTING THE QUIZ ANSWERS I THINK
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
