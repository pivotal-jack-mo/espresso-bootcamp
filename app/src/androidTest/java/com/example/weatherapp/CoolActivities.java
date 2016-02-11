package com.example.weatherapp;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.SystemClock;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;

import com.example.weatherapp.activities.DetailsActivity;
import com.example.weatherapp.activities.MainActivity;
import com.example.weatherapp.data.WeatherContract;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getContext;
import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.InstrumentationRegistry.getTargetContext;

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
    public void customIntentToStartActivity() {
        WeatherAppSharedPrefs sharedPrefs = new WeatherAppSharedPrefs(getTargetContext());
        Instrumentation.ActivityMonitor monitor =  getInstrumentation().addMonitor(DetailsActivity.class.getName(), null, false);
        Activity currentActivity = getInstrumentation().waitForMonitorWithTimeout(monitor, 5);
        View v = currentActivity.findViewById(R.id.recyclerview_forecast);
        int columnIndex = v.getCursor().getColumnIndex(WeatherContract.WeatherEntry.COLUMN_DATE);
        Uri weatherUri = WeatherContract.buildWeatherLocationWithDate(sharedPrefs.getLocationPrefs(), getCursor().getLong(columnIndex));
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setData(weatherUri);
        mActivityRule.launchActivity(intent);
    }

}
