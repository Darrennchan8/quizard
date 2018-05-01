package cmsc355.groupF.quizard;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class NewQuestionFragment extends Fragment {

    private OnDecisionListener mListener = null;

    public interface OnDecisionListener {
        void addQuestion();
        void publishQuiz();
    }

    public NewQuestionFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_question, container, false);
    }

    @Override
    @SuppressWarnings("ConstantConditions")
    public void onStart() {
        super.onStart();
        FloatingActionButton btnAdd = getView().findViewById(R.id.btn_add);
        Button btnPublish = getView().findViewById(R.id.btn_publish);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.addQuestion();
                }
            }
        });
        btnPublish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.publishQuiz();
                }
            }
        });
    }

    public void setOnDecisionListener(OnDecisionListener listener) {
        this.mListener = listener;
    }

}
