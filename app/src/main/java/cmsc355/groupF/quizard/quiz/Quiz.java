package cmsc355.groupF.quizard.quiz;

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

    public double grade() {
        int correct = 0;
        for (Question q : this.questions) {
            correct += q.isStudentCorrect() ? 1 : 0;
        }
        return 100.0 * correct / this.questions.size();
    }

    public static Quiz mockQuiz() {
        Quiz quiz = new Quiz("Presidents Quiz", "Presidents!");
        quiz.addQuestion(new Question("Who is the president of the United States?", new ArrayList<MultipleChoiceAnswer>() {{
            add(new MultipleChoiceAnswer("George Washington"));
            add(new MultipleChoiceAnswer("Abraham Lincoln"));
            add(new MultipleChoiceAnswer("Donald Trump", true));
            add(new MultipleChoiceAnswer("Barack Obama"));
        }}));
        quiz.addQuestion(new Question("Who was a past US president?", new ArrayList<MultipleChoiceAnswer>() {{
            add(new MultipleChoiceAnswer("George Washington", true));
            add(new MultipleChoiceAnswer("Cleopatra"));
            add(new MultipleChoiceAnswer("Barack Obama", true));
            add(new MultipleChoiceAnswer("Putin"));
        }}));
        quiz.addQuestion(new Question("Who was the president before Donald Trump?", "Barack Obama"));
        quiz.addQuestion(new Question("Do you want extra points?", new ArrayList<MultipleChoiceAnswer>() {{
            add(new MultipleChoiceAnswer("YES!!!", true));
            add(new MultipleChoiceAnswer("no"));
        }}));
        quiz.addQuestion(new Question("Which president served two non-consecutive terms?", new ArrayList<MultipleChoiceAnswer>() {{
            add(new MultipleChoiceAnswer("Grover Cleveland", true));
            add(new MultipleChoiceAnswer("James Monroe"));
            add(new MultipleChoiceAnswer("Barack Obama"));
            add(new MultipleChoiceAnswer("Ronald Reagan"));
        }}));
        quiz.addQuestion(new Question("Which president was married to a first lady named Ladybird?", new ArrayList<MultipleChoiceAnswer>() {{
            add(new MultipleChoiceAnswer("George Washington"));
            add(new MultipleChoiceAnswer("James K. Polk"));
            add(new MultipleChoiceAnswer("Rutherford B. Hayes"));
            add(new MultipleChoiceAnswer("Lyndon Johnson", true));
        }}));
        return quiz;
    }

}
