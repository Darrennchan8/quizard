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
        this(QuestionType.MULTIPLE_CHOICE, questionText);
        this.setMultipleChoiceAnswers(multipleChoiceAnswers);
    }

    public Question(@NonNull String questionText, @NonNull String shortAnswer) {
        this(QuestionType.SHORT_ANSWER, questionText);
        this.setShortAnswer(shortAnswer);
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

    public void setMultipleChoiceAnswers(List<MultipleChoiceAnswer> answers) {
        this.setQuestionType(QuestionType.MULTIPLE_CHOICE);
        this.setQuestionType(QuestionType.MULTIPLE_CHOICE);
        this.multipleChoiceAnswers = new ArrayList<>(answers);
    }

    public void addMultipleChoiceAnswer(@NonNull MultipleChoiceAnswer multipleChoiceAnswer) {
        this.setQuestionType(QuestionType.MULTIPLE_CHOICE);
        this.multipleChoiceAnswers.add(multipleChoiceAnswer);
    }

    public String getShortAnswer() {
        return shortAnswer;
    }

    public void setShortAnswer(@NonNull String shortAnswer) {
        this.setQuestionType(QuestionType.SHORT_ANSWER);
        this.shortAnswer = shortAnswer;
    }

    public boolean isCorrect(String answer) {
        switch (getQuestionType()) {
            case MULTIPLE_CHOICE:
                for (MultipleChoiceAnswer option : getMultipleChoiceAnswers()) {
                    if (option.isCorrect() && option.equals(answer)) {
                        return true;
                    }
                }
                return false;
            case SHORT_ANSWER:
            default:
                return this.getShortAnswer().equals(answer);
        }
    }

    public boolean isCorrect(int answerIndex) {
        return this.getQuestionType() == QuestionType.MULTIPLE_CHOICE &&
                answerIndex >= 0 && answerIndex < this.multipleChoiceAnswers.size()
                && isCorrect(this.multipleChoiceAnswers.get(answerIndex).getText());
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
