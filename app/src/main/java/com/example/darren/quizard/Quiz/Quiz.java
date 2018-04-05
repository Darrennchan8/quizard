package com.example.darren.quizard.Quiz;

import java.util.ArrayList;
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

    public static Quiz mockQuiz() {
        Quiz quiz = new Quiz("Presidents Quiz", "Presidents!");
        quiz.addQuestion(new Question(QuestionType.MULTIPLE_CHOICE, "Who is the president of the United States?", new ArrayList<MultipleChoiceAnswer>() {{
            add(new MultipleChoiceAnswer("George Washington"));
            add(new MultipleChoiceAnswer("Abraham Lincoln"));
            add(new MultipleChoiceAnswer("Donald Trump", true));
            add(new MultipleChoiceAnswer("Barack Obama"));
        }}));
        quiz.addQuestion(new Question(QuestionType.MULTIPLE_CHOICE, "Who was a past US president?", new ArrayList<MultipleChoiceAnswer>() {{
            add(new MultipleChoiceAnswer("George Washington", true));
            add(new MultipleChoiceAnswer("Cleopatra"));
            add(new MultipleChoiceAnswer("Barack Obama", true));
            add(new MultipleChoiceAnswer("Putin"));
        }}));
        quiz.addQuestion(new Question(QuestionType.SHORT_ANSWER, "Who was the president before Donald Trump?", "Barack Obama"));
        quiz.addQuestion(new Question(QuestionType.MULTIPLE_CHOICE, "Do you want extra points?", new ArrayList<MultipleChoiceAnswer>() {{
            add(new MultipleChoiceAnswer("YES!!!", true));
            add(new MultipleChoiceAnswer("no"));
        }}));
        return quiz;
    }
}
