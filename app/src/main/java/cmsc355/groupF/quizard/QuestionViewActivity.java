package cmsc355.groupF.quizard;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.inputmethod.InputMethodManager;

import com.google.firebase.database.DatabaseError;

import java.util.List;

import cmsc355.groupF.quizard.quiz.Quiz;
import cmsc355.groupF.quizard.quiz.QuizUtils;

public class QuestionViewActivity extends AppCompatActivity {

    public static class SubmitStatus {
        public static final int SUBMITTED = 1;
    }

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
                questionViewPageAdapter.setOnQuizSubmitListener(new SubmitQuizFragment.QuizSubmitListener() {
                    @Override
                    public void onSubmit() {
                        AlertDialog dialog = new AlertDialog.Builder(QuestionViewActivity.this)
                                .setTitle(R.string.results)
                                .setMessage(getString(R.string.you_received_percentage, Math.round(targetQuiz.grade())))
                                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        finish();
                                    }
                                })
                                .create();
                        dialog.show();
                    }
                });
                final ViewPager viewPager =
                        new ViewPagerBuilder(QuestionViewActivity.this).setPageChangeHook(new ViewPagerBuilder.PageChangeHook() {
                                    @Override
                                    public String getAppBarText(int position, int total) {
                                        return position < total ?
                                                getString(R.string.question_i_of_n, position, total - 1) :
                                                targetQuiz.getTitle();
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
    }
}
