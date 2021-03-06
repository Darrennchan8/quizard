package cmsc355.groupF.quizard.quiz;

public class MultipleChoiceAnswer {
    private String text;
    private boolean correct;
    private boolean studentChoice;

    public MultipleChoiceAnswer(String text, boolean correct) {
        this.setText(text);
        this.setCorrect(correct);
        this.setStudentChoice(false);
    }

    public MultipleChoiceAnswer(String text) {
        this(text, false);
    }

    public MultipleChoiceAnswer() {
        this("");
    }

    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    public boolean getStudentChoice() {
        return this.studentChoice;
    }

    public void setStudentChoice(boolean selected) {
        this.studentChoice = selected;
    }

    public boolean isStudentCorrect() {
        return isCorrect() == getStudentChoice();
    }

    @Override
    public boolean equals(Object obj) {
        return obj == this ||
                obj instanceof MultipleChoiceAnswer && this.getText().equals(((MultipleChoiceAnswer) obj).getText()) ||
                obj instanceof String && this.getText().equals(obj);
    }

    @Override
    public String toString() {
        return "MultipleChoiceAnswer{" +
                "correct=" + correct +
                ", text='" + text + '\'' +
                '}';
    }
}
