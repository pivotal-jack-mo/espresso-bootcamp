package com.example.weatherapp;

import android.os.SystemClock;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.example.weatherapp.activities.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.swipeDown;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.is;


/**
 * Created by pivotal on 2016-01-18.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class HelloWorldEspressoTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule(MainActivity.class);
    String location = "Toronto,US";

//    //Task 0
//    @Test
//    public void sleep() {
//        SystemClock.sleep(20000);
//    }
//
//    //Task 1
//    @Test
//    public void forecastPreference() {
//        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
//        onView(withText(R.string.action_settings)).perform(click());
//        onView(withText(R.string.preference_title)).check(matches(withText("Forecast Preference")));
//    }
//
//    //Task 2
//    @Test
//    public void changeLocation() {
//        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
//        onView(withText(R.string.action_settings)).perform(click());
//        onView(withText(R.string.preference_zip_title)).perform(click());
//        onView(allOf(withClassName(endsWith("EditText")), withText(R.string.preference_zip_default))).perform(replaceText(location));
//        onView(withText("OK")).perform(click());
//        onView(withText(location)).check(matches(withText(location)));
//    }

    //Task 3
    @Test
    public void refresh() {
        onView(withId(R.id.refresh_layout)).perform(swipeDown());
        onView(allOf(withId(android.support.design.R.id.snackbar_text), withText(location)))
                .check(matches(isDisplayed()));
    }
}