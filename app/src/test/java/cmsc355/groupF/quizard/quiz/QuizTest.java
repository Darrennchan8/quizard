package cmsc355.groupF.quizard.quiz;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class QuizTest {
    @Test
    public void testMultipleChoiceAnswer() {
        // Test constructors
        MultipleChoiceAnswer multipleChoiceAnswer = new MultipleChoiceAnswer();
        assertEquals("", multipleChoiceAnswer.getText());
        assertFalse(multipleChoiceAnswer.isCorrect());
        multipleChoiceAnswer = new MultipleChoiceAnswer("True");
        assertEquals("True", multipleChoiceAnswer.getText());
        assertFalse(multipleChoiceAnswer.isCorrect());
        multipleChoiceAnswer = new MultipleChoiceAnswer("False", true);
        assertEquals("False", multipleChoiceAnswer.getText());
        assertTrue(multipleChoiceAnswer.isCorrect());
        // Test getters and setters
        multipleChoiceAnswer = new MultipleChoiceAnswer("Acid");
        assertEquals("Acid", multipleChoiceAnswer.getText());
        assertFalse(multipleChoiceAnswer.isCorrect());
        multipleChoiceAnswer.setText("Base");
        multipleChoiceAnswer.setCorrect(true);
        assertEquals("Base", multipleChoiceAnswer.getText());
        assertTrue(multipleChoiceAnswer.isCorrect());
        // Test toString()
        multipleChoiceAnswer = new MultipleChoiceAnswer("Barack Obama", true);
        assertEquals("MultipleChoiceAnswer{correct=true, text='Barack Obama'}",
                multipleChoiceAnswer.toString());
        // Test equals()
        assertTrue(multipleChoiceAnswer.equals(new MultipleChoiceAnswer("Barack Obama")));
        assertTrue(multipleChoiceAnswer.equals("Barack Obama"));
    }

    @Test
    public void testQuestion() {
        // Test constructors
        Question question = new Question();
        assertTrue(question.getQuestionText().isEmpty());
        assertEquals(QuestionType.MULTIPLE_CHOICE, question.getQuestionType());
        assertTrue(question.getMultipleChoiceAnswers().isEmpty());

        question = new Question(QuestionType.SHORT_ANSWER);
        assertTrue(question.getQuestionText().isEmpty());
        assertEquals(QuestionType.SHORT_ANSWER, question.getQuestionType());
        assertTrue(question.getShortAnswer().isEmpty());

        question = new Question(QuestionType.SHORT_ANSWER, "Who is the president of the United States?");
        assertEquals("Who is the president of the United States?", question.getQuestionText());
        assertEquals(QuestionType.SHORT_ANSWER, question.getQuestionType());
        assertTrue(question.getShortAnswer().isEmpty());

        question = new Question("Who is the president of the United States?", "Donald Trump");
        assertEquals("Who is the president of the United States?", question.getQuestionText());
        assertEquals(QuestionType.SHORT_ANSWER, question.getQuestionType());
        assertEquals("Donald Trump", question.getShortAnswer());

        ArrayList<MultipleChoiceAnswer> answers = new ArrayList<>();
        answers.add(new MultipleChoiceAnswer("Barack Obama", false));
        answers.add(new MultipleChoiceAnswer("Vladamir Putin"));
        answers.add(new MultipleChoiceAnswer("Donald Trump", true));
        answers.add(new MultipleChoiceAnswer("Cleopatra"));
        question = new Question("Who is the president of the United States?", answers);
        assertEquals("Who is the president of the United States?", question.getQuestionText());
        assertEquals(QuestionType.MULTIPLE_CHOICE, question.getQuestionType());
        assertTrue(answers.equals(question.getMultipleChoiceAnswers()));

        // Test getters and setters
        question.setQuestionText("Who is the president of Russia?");
        question.setShortAnswer("Vladamir Putin");
        assertEquals("Who is the president of Russia?", question.getQuestionText());
        assertEquals(QuestionType.SHORT_ANSWER, question.getQuestionType());
        assertEquals("Vladamir Putin", question.getShortAnswer());

        // Test addMultipleChoiceQuestion && isCorrect
        assertTrue(question.isCorrect("Vladamir Putin"));
        assertFalse(question.isCorrect(1));
        answers.get(1).setCorrect(true);
        assertFalse(question.isCorrect(1));
        question.addMultipleChoiceAnswer(new MultipleChoiceAnswer("Just give me points!", true));
        assertTrue(question.isCorrect("Just give me points!"));
        assertTrue(question.isCorrect("Vladamir Putin"));
        assertTrue(question.isCorrect(1));
        assertFalse(question.isCorrect(0));
        assertFalse(question.isCorrect("Barack Obama"));

        // Test toString()
        assertEquals("Question{" +
                        "questionType=MULTIPLE_CHOICE, " +
                        "questionText='Who is the president of Russia?', " +
                        "multipleChoiceAnswers=[" +
                        "MultipleChoiceAnswer{" +
                        "correct=false, " +
                        "text='Barack Obama'}, " +
                        "MultipleChoiceAnswer{" +
                        "correct=true, " +
                        "text='Vladamir Putin'}, " +
                        "MultipleChoiceAnswer{" +
                        "correct=true, " +
                        "text='Donald Trump'}, " +
                        "MultipleChoiceAnswer{" +
                        "correct=false, " +
                        "text='Cleopatra'}, " +
                        "MultipleChoiceAnswer{" +
                        "correct=true, " +
                        "text='Just give me points!'}], " +
                        "shortAnswer='Vladamir Putin'}",
                question.toString());
    }
}