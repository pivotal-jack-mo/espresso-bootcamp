package com.example.weatherapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.example.weatherapp.activities.DetailsActivity;
import com.example.weatherapp.data.WeatherContract;
import com.example.weatherapp.data.WeatherProvider;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasData;
import static android.support.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

/**
 * Created by pivotal on 2016-01-26.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class IntenseIntents {
    @Rule
    public IntentsTestRule<DetailsActivity> mActivityRule = new IntentsTestRule<>(DetailsActivity.class);

    @Inject
    WeatherAppSharedPrefs sharedPrefs;

    @Test
    //Task 8
    public void sendLocation() {
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
        onView(withText(R.string.action_map)).perform(click());

        String locationSetting = sharedPrefs.getLocationPrefs();
        Uri weatherForLocationUri = WeatherContract.buildWeatherLocation(
                locationSetting);

        intended(allOf(
                hasAction(Intent.ACTION_VIEW),
                hasData(weatherForLocationUri)
        ));

        SharedPreferences preferences = mActivityRule.getActivity().getSharedPreferences("Mypref", 0);
        preferences.edit().clear().commit();
    }
}
