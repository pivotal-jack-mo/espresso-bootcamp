package com.example.weatherapp;

import android.content.Intent;
import android.net.Uri;
import android.os.SystemClock;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.example.weatherapp.activities.DetailsActivity;
import com.example.weatherapp.data.WeatherContract;
import com.example.weatherapp.services.WeatherService;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getContext;
import static android.support.test.InstrumentationRegistry.getTargetContext;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by pivotal on 2016-01-29.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class CoolActivities {

    @Rule
    public ActivityTestRule<DetailsActivity> mActivityRule = new ActivityTestRule(DetailsActivity.class,false,false);

    //Task 9
    @Test
    public void detailActivity() {

        WeatherAppSharedPrefs sharedPrefs = new WeatherAppSharedPrefs(getTargetContext());
        getTargetContext().getSharedPreferences("com.example.weatherapp_preferences", 0).edit().clear().commit();
        sharedPrefs.setLocationPrefs("Toronto,CA");

        long currentDay = new DateTime().withZone(DateTimeZone.getDefault()).withTimeAtStartOfDay().getMillis();
        Uri weatherUri = WeatherContract.buildWeatherLocationWithDate(sharedPrefs.getLocationPrefs(), currentDay);

        Intent intent = new Intent(getTargetContext(), DetailsActivity.class);
        intent.setData(weatherUri);
        intent.putExtra(WeatherService.LOCATION_SETTING_EXTRA, sharedPrefs.getLocationPrefs());
        mActivityRule.launchActivity(intent);

        intent = new Intent(getContext(), WeatherService.class);
        intent.putExtra(WeatherService.LOCATION_SETTING_EXTRA, sharedPrefs.getLocationPrefs());
        getTargetContext().startService(intent);

        SystemClock.sleep(8000);

        onView(withId(R.id.detail_day_textview)).check(matches(isDisplayed()));
    }
}
