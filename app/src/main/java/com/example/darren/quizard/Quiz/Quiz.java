package com.example.darren.quizard.Quiz;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Darren on 3/27/2018.
 * This object represents a quiz object.
 */

public class Quiz {
    private String title;
    private String password;

    private List<Question> questions;

    public Quiz(String title, String password) {
        this.setTitle(title);
        this.setPassword(password);
        this.questions = new LinkedList<>();
    }

    public Quiz(String title) {
        this(title, "");
    }

    public Quiz() {
        this("");
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Question> getQuestions() {
        return Collections.unmodifiableList(this.questions);
    }

    public void addQuestion(Question question) {
        this.questions.add(question);
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "title='" + title + '\'' +
                ", password='" + password + '\'' +
                ", questions=" + questions +
                '}';
    }
}
