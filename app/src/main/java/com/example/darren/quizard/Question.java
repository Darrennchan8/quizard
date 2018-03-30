package com.example.darren.quizard;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Darren on 3/27/2018.
 */

public class Question {
    private String mQuestion;
    private List<String> mAnswers;

    public Question(String question, Collection<String> answers) {
        this.setQuestion(question);
        this.setAnswers(answers);
    }
    public Question(String question) {
        this(question, new ArrayList<String>());
    }
    public Question() {
        this("Untitled Question");
    }
    public void setQuestion(String question) {
        this.mQuestion = question;
    }
    public String getQuestion() {
        return this.mQuestion;
    }
    public void setAnswers(Collection<String> mAnswers) {
        this.mAnswers = new ArrayList<>(mAnswers);
    }
    public List<String> getAnswers() {
        return new ArrayList<>(mAnswers);
    }
}
