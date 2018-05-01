package com.example.darren.assignment4;

import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import cmsc355.groupF.quizard.CreateQuizActivity;
import cmsc355.groupF.quizard.FindQuizActivity;
import cmsc355.groupF.quizard.MainActivity;
import cmsc355.groupF.quizard.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {
    @Rule
    public IntentsTestRule<MainActivity> intentsTestRule = new IntentsTestRule<>(MainActivity.class);

    @Test
    public void testStudentButton() {
        onView(withId(R.id.btn_student)).perform(click());
        intended(hasComponent(FindQuizActivity.class.getName()));
    }

    @Test
    public void testTeacherButton() {
        onView(withId(R.id.btn_teacher)).perform(click());
        intended(hasComponent(CreateQuizActivity.class.getName()));
    }

}
