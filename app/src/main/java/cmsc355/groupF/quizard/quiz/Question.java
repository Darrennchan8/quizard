package cmsc355.groupF.quizard.quiz;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Question {

    private QuestionType questionType;
    private String questionText;
    private List<MultipleChoiceAnswer> multipleChoiceAnswers;
    private String shortAnswer;

    public Question() {
        this(QuestionType.MULTIPLE_CHOICE);
    }

    public Question(@NonNull QuestionType questionType) {
        this(questionType, "");
    }

    public Question(@NonNull QuestionType questionType, @NonNull String questionText) {
        this.questionType = questionType;
        this.questionText = questionText;
        this.multipleChoiceAnswers = new ArrayList<>();
        this.shortAnswer = "";
    }

    public Question(@NonNull String questionText, @NonNull List<MultipleChoiceAnswer> multipleChoiceAnswers) {
        this.questionType = QuestionType.MULTIPLE_CHOICE;
        this.questionText = questionText;
        this.multipleChoiceAnswers = new ArrayList<>(multipleChoiceAnswers);
        this.shortAnswer = "";
    }

    public Question(@NonNull String questionText, @NonNull String shortAnswer) {
        this.questionType = QuestionType.SHORT_ANSWER;
        this.questionText = questionText;
        this.multipleChoiceAnswers = new ArrayList<>();
        this.shortAnswer = shortAnswer;
    }

    public QuestionType getQuestionType() {
        return questionType;
    }

    public void setQuestionType(@NonNull QuestionType questionType) {
        this.questionType = questionType;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(@NonNull String questionText) {
        this.questionText = questionText;
    }

    public List<MultipleChoiceAnswer> getMultipleChoiceAnswers() {
        return Collections.unmodifiableList(multipleChoiceAnswers);
    }

    public void addMultipleChoiceAnswer(@NonNull MultipleChoiceAnswer multipleChoiceAnswer) {
        this.multipleChoiceAnswers.add(multipleChoiceAnswer);
    }

    public String getShortAnswer() {
        return shortAnswer;
    }

    public void setShortAnswer(@NonNull String shortAnswer) {
        this.shortAnswer = shortAnswer;
    }

    @Override
    public String toString() {
        return "Question{" +
                "questionType=" + questionType +
                ", questionText='" + questionText + '\'' +
                ", multipleChoiceAnswers=" + multipleChoiceAnswers +
                ", shortAnswer='" + shortAnswer + '\'' +
                '}';
    }
}
