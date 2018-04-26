package cmsc355.groupF.quizard.quiz;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class QuizUtils {
    public static final String QUIZZES_ARRAY_KEY = "quizzes";

    public interface QuizQueryCallback {
        void onLoad(List<Quiz> quizzes);

        void onError(DatabaseError err);
    }

    public static void publish(Quiz quiz, DatabaseReference.CompletionListener completionListener) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference()
                .child(QUIZZES_ARRAY_KEY).push();
        ref.setValue(quiz, completionListener);
    }

    public static void getAllQuizzes(final QuizQueryCallback callback) {
        DatabaseReference quizzes = FirebaseDatabase.getInstance().getReference().child(QUIZZES_ARRAY_KEY);
        quizzes.keepSynced(true);
        quizzes.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Quiz> quizzes = new ArrayList<>((int) dataSnapshot.getChildrenCount());
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    Quiz quiz = child.getValue(Quiz.class);
                    quizzes.add(quiz);
                }
                callback.onLoad(quizzes);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                callback.onError(databaseError);
            }
        });
    }

}
