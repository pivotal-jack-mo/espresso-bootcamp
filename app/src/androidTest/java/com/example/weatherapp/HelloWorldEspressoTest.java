package com.example.weatherapp;


import android.content.SharedPreferences;
import android.os.SystemClock;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.example.weatherapp.activities.MainActivity;
import com.example.weatherapp.data.WeatherContract;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeDown;
import static android.support.test.espresso.action.ViewActions.swipeUp;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.CursorMatchers.withRowDouble;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.example.weatherapp.MyCustomMatchers.withNumberOfItems;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasToString;


/**
 * Created by pivotal on 2016-01-18.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class HelloWorldEspressoTest{

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule(MainActivity.class);
    String location = "Toronto,US";

    //Task 0
    @Test
    public void sleep() {
        SystemClock.sleep(20000);
    }

    //Task 1
    @Test
    public void forecastPreference() {
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
        onView(withText(R.string.action_settings)).perform(click());
        onView(withText(R.string.preference_title)).check(matches(isDisplayed()));
    }

    //Task 2
    @Test
    public void changeLocation() {
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
        onView(withText(R.string.action_settings)).perform(click());
        onView(withText(R.string.preference_zip_title)).perform(click());
        onView(withId(android.R.id.edit)).perform(clearText()).perform(typeText(location));
        onView(withText("OK")).perform(click());
        onView(withText(location)).check(matches(isDisplayed()));
    }

    //Task 3
    @Test
    public void refresh() {
        onView(withId(R.id.refresh_layout)).perform(swipeDown());
        onView(allOf(withId(android.support.design.R.id.snackbar_text), withText(location))).check(matches(isDisplayed()));
    }


    //Task 4
    @Test
    public void changeTempUnits() {
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
        onView(withText(R.string.action_settings)).perform(click());
        onView(withText("Temperature Units")).perform(click());
        String tempUnit = mActivityRule.getActivity().getString(R.string.units_imperial);
        onData(hasToString(tempUnit)).perform(click());
    }

    //Task 5
    @Ignore
    public void todaysForcast() {
        Calendar calendar = Calendar.getInstance();
        int date = calendar.get(Calendar.DATE);
        onData(withRowDouble(WeatherContract.WeatherEntry.COLUMN_DATE, date)).perform(click());
    }

    //Task 6
    @Test
    public void clickLastForecast() {
        onView(withId(R.id.refresh_layout)).perform(swipeUp());
        SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE", Locale.US);
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.add(Calendar.DATE, 6);
        String day = dayFormat.format(calendar.getTime());
        onView(withId(R.id.recyclerview_forecast)).perform(RecyclerViewActions.actionOnItem(hasDescendant(withText(day)), click()));
    }

    //Task 7
    @Test
    public void checkNumberOfViews() {
        onView(withId(R.id.recyclerview_forecast)).check(matches(withNumberOfItems(7)));
    }
}