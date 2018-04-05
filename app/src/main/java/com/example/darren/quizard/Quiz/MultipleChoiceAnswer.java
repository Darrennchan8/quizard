package com.example.darren.quizard.Quiz;

public class MultipleChoiceAnswer {
    private boolean correct;
    private String text;

    public MultipleChoiceAnswer(boolean correct, String text) {
        this.setCorrect(correct);
        this.setText(text);
    }

    public MultipleChoiceAnswer(String text) {
        this(false, text);
    }

    public MultipleChoiceAnswer() {
        this("");
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "MultipleChoiceAnswer{" +
                "correct=" + correct +
                ", text='" + text + '\'' +
                '}';
    }
}
