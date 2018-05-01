package cmsc355.groupF.quizard;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class SubmitQuizFragment extends Fragment {

    private QuizSubmitListener mListener;

    public interface QuizSubmitListener {
        void submitQuiz();
    }

    public SubmitQuizFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_submit_quiz, container, false);
    }
    @Override
    @SuppressWarnings("ConstantConditions")
    public void onStart() {
        super.onStart();
        Button btnSubmit = getView().findViewById(R.id.btn_submit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.submitQuiz();
                }
            }
        });
    }

    public void setQuizSubmitListener(QuizSubmitListener listener) {
        this.mListener = listener;
    }

}


