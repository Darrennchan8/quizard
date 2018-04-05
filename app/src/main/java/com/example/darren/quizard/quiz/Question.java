package com.example.darren.quizard.quiz;

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

    public Question(QuestionType questionType) {
        this(questionType, "");
    }

    public Question(QuestionType questionType, String questionText) {
        this.questionType = questionType;
        this.questionText = questionText;
        this.multipleChoiceAnswers = questionType == QuestionType.MULTIPLE_CHOICE ? new ArrayList<MultipleChoiceAnswer>() : null;
        this.shortAnswer = questionType == QuestionType.SHORT_ANSWER ? "" : null;
    }

    public Question(QuestionType questionType, String questionText,
                    List<MultipleChoiceAnswer> multipleChoiceAnswers) {
        this.questionType = questionType;
        this.questionText = questionText;
        this.multipleChoiceAnswers = new ArrayList<>(multipleChoiceAnswers);
        this.shortAnswer = null;
    }

    public Question(QuestionType questionType, String questionText,
                    String shortAnswer) {
        this.questionType = questionType;
        this.questionText = questionText;
        this.multipleChoiceAnswers = null;
        this.shortAnswer = shortAnswer;
    }

    public QuestionType getQuestionType() {
        return questionType;
    }

    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public List<MultipleChoiceAnswer> getMultipleChoiceAnswers() {
        return Collections.unmodifiableList(multipleChoiceAnswers);
    }

    public void addMultipleChoiceAnswer(MultipleChoiceAnswer multipleChoiceAnswer) {
        this.multipleChoiceAnswers.add(multipleChoiceAnswer);
    }

    public String getShortAnswer() {
        return shortAnswer;
    }

    public void setShortAnswer(String shortAnswer) {
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
