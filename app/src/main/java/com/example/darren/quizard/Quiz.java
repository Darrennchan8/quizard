package com.example.darren.quizard;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Darren on 3/27/2018.
 * This object represents a quiz object.
 */

public class Quiz {
    private JSONObject mQuiz = new JSONObject();

    public Quiz(String name, Collection<Question> questions) {
        this.setName(name);
        this.setQuestions(questions);
    }
    public Quiz(String name) {
        this(name, new ArrayList<Question>());
    }
    public Quiz() {
        this("Untitled Quiz");
    }

    public String getName() {
        try {
            return this.mQuiz.getString("name");
        } catch (JSONException impossible) {
            Log.wtf(Quiz.class.getName(), impossible);
        }
        return null;
    }
    public void setName(String newName) {
        if (newName == null) {
            throw new NullPointerException();
        }
        if (newName.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty.");
        }
        try {
            this.mQuiz.put("name", newName);
        } catch (JSONException impossible) {
            Log.wtf(Quiz.class.getName(), impossible);
        }
    }

    public List<Question> getQuestions() {
        try {
            JSONArray ref = this.mQuiz.getJSONArray("questions");
            List<Question> questions = new ArrayList<>(ref.length());
            for (int i = 0; i != ref.length(); i++) {
                questions.add((Question)ref.get(i));
            }
            return questions;
        } catch (JSONException impossible) {
            Log.wtf(Quiz.class.getName(), impossible);
        }
        return null;
    }

    public void setQuestions(Collection<Question> questions) {
        if (questions == null) {
            throw new NullPointerException();
        }
        try {
            this.mQuiz.put("questions", new JSONArray(questions));
        } catch (JSONException impossible) {
            Log.wtf(Quiz.class.getName(), impossible);
        }
    }

    public void addQuestion(Question question) {
        if (question == null) {
            throw new NullPointerException();
        }
        try {
            this.mQuiz.getJSONArray("questions").put(question);
        } catch (JSONException impossible) {
            Log.wtf(Quiz.class.getName(), impossible);
        }
    }

    @Override
    public String toString() {
        return this.mQuiz.toString();
    }
}
