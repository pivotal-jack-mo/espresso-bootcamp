package com.example.weatherapp.Activities;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.os.SystemClock;
import android.provider.BaseColumns;
import android.provider.UserDictionary;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.example.weatherapp.R;
import com.example.weatherapp.WeatherAppSharedPrefs;
import com.example.weatherapp.activities.DetailsActivity;
import com.example.weatherapp.data.WeatherContract;
import com.example.weatherapp.models.City;
import com.example.weatherapp.models.Forecast;
import com.example.weatherapp.models.ForecastList;
import com.example.weatherapp.services.ForecastRetrofitService;
import com.example.weatherapp.services.WeatherService;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.ArrayList;

import javax.inject.Inject;

import retrofit.Response;

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
public class DetailsActivityTest {

//    public static final String CONTENT_AUTHORITY = "com.example.android.sunshine.app";
//    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
//    public static final String PATH_WEATHER = "weather";
//    public static final String PATH_LOCATION = "location";
//
//    public static final Uri CONTENT_URI =
//            BASE_CONTENT_URI.buildUpon().appendPath(PATH_WEATHER).build();

    public static final String LOCATION_SETTING_EXTRA = "location_setting_extra";


    private static final SQLiteQueryBuilder weatherQueryBuilder = new SQLiteQueryBuilder();

//    @Inject
//    ForecastRetrofitService forecastRetrofitService;

    @Rule
    public ActivityTestRule<DetailsActivity> mActivityRule = new ActivityTestRule(DetailsActivity.class,false,false);

    @Before
    public void setup() {
        WeatherAppSharedPrefs sharedPrefs = new WeatherAppSharedPrefs(getTargetContext());
        getTargetContext().getSharedPreferences("com.example.weatherapp_preferences", 0).edit().clear().commit();
        sharedPrefs.setLocationPrefs("Toronto,CA");

//        long currentDay = new DateTime().withZone(DateTimeZone.getDefault()).withTimeAtStartOfDay().getMillis();
//        Uri weatherUri = WeatherContract.buildWeatherLocationWithDate(sharedPrefs.getLocationPrefs(), currentDay);

//        ContentResolver resolver = getContext().getContentResolver();
//
//        ContentValues values = new ContentValues();
//        values.put(WeatherContract.);
//        resolver.insert(CONTENT_URI, values);

        WeatherService test = new WeatherService();

        ContentResolver resolver = test.getContentResolver();

        ArrayList<ContentValues> forecastValues = new ArrayList<>();
        ContentValues forecastValue = new ContentValues();
        forecastValue.put(WeatherContract.WeatherEntry.COLUMN_LOC_KEY, "test");
        forecastValue.put(WeatherContract.WeatherEntry.COLUMN_DATE, "test");
        forecastValue.put(WeatherContract.WeatherEntry.COLUMN_HUMIDITY, "test");
        forecastValue.put(WeatherContract.WeatherEntry.COLUMN_PRESSURE, "test");
        forecastValue.put(WeatherContract.WeatherEntry.COLUMN_WIND_SPEED, "test");
        forecastValue.put(WeatherContract.WeatherEntry.COLUMN_DEGREES, "test");
        forecastValue.put(WeatherContract.WeatherEntry.COLUMN_MAX_TEMP, "test");
        forecastValue.put(WeatherContract.WeatherEntry.COLUMN_MIN_TEMP, "test");
        forecastValue.put(WeatherContract.WeatherEntry.COLUMN_SHORT_DESC, "test");
        forecastValue.put(WeatherContract.WeatherEntry.COLUMN_WEATHER_ID, "test");
        forecastValues.add(forecastValue);

        resolver.bulkInsert(WeatherContract.WeatherEntry.CONTENT_URI, forecastValues.toArray(new ContentValues[forecastValues.size()]));
    }

    //Task 9
    @Test
    public void detailActivity() throws IOException {

        WeatherAppSharedPrefs sharedPrefs = new WeatherAppSharedPrefs(getTargetContext());
        getTargetContext().getSharedPreferences("com.example.weatherapp_preferences", 0).edit().clear().commit();
        sharedPrefs.setLocationPrefs("Toronto,CA");

        long currentDay = new DateTime().withZone(DateTimeZone.getDefault()).withTimeAtStartOfDay().getMillis();
        Uri weatherUri = WeatherContract.buildWeatherLocationWithDate(sharedPrefs.getLocationPrefs(), currentDay);

        Intent intent = new Intent(getTargetContext(), DetailsActivity.class);
        intent.setData(weatherUri);
        intent.putExtra(WeatherService.LOCATION_SETTING_EXTRA, sharedPrefs.getLocationPrefs());
        mActivityRule.launchActivity(intent);

//        intent = new Intent(getContext(), WeatherService.class);
//        intent.putExtra(WeatherService.LOCATION_SETTING_EXTRA, sharedPrefs.getLocationPrefs());
//        getTargetContext().startService(intent);

//        String locationSetting = intent.getStringExtra(LOCATION_SETTING_EXTRA);
//        WeatherService test = new WeatherService();
//
//        Response<Forecast> forecastResponse = forecastRetrofitService.getForecast(locationSetting).execute();
//        Forecast forecast = forecastResponse.body();
//        City location = forecast.getCity();
//        Long locationId = test.addLocationToTheDatabase(location.getName(), location.getCountry(), locationSetting, location.getCoordinates().getLatitude(), location.getCoordinates().getLongitude());
//        ArrayList<ForecastList> forecastList = forecast.getForecastList();
//
//        Uri uri = WeatherContract.WeatherEntry.CONTENT_URI;
//        String selection = WeatherContract.WeatherEntry.COLUMN_LOC_KEY + "=?";
//        String[] selectionArgs = new String[]{String.valueOf(locationId)};
//
//        Cursor weatherCursor = test.getContentResolver()
//                .query(uri, null, selection, selectionArgs, null);
//
//        if (weatherCursor.moveToFirst()) {
//            test.getContentResolver().delete(uri, selection, selectionArgs);
//        }
//
//        weatherCursor.close();
//
//        ArrayList<ContentValues> forecastValues = new ArrayList<>();
//        for (ForecastList dailyForecast : forecastList) {
//            ContentValues forecastValue = new ContentValues();
//            forecastValue.put(WeatherContract.WeatherEntry.COLUMN_LOC_KEY, "test");
//            forecastValue.put(WeatherContract.WeatherEntry.COLUMN_DATE, "test");
//            forecastValue.put(WeatherContract.WeatherEntry.COLUMN_HUMIDITY, "test");
//            forecastValue.put(WeatherContract.WeatherEntry.COLUMN_PRESSURE, "test");
//            forecastValue.put(WeatherContract.WeatherEntry.COLUMN_WIND_SPEED, "test");
//            forecastValue.put(WeatherContract.WeatherEntry.COLUMN_DEGREES, "test");
//            forecastValue.put(WeatherContract.WeatherEntry.COLUMN_MAX_TEMP, "test");
//            forecastValue.put(WeatherContract.WeatherEntry.COLUMN_MIN_TEMP, "test");
//            forecastValue.put(WeatherContract.WeatherEntry.COLUMN_SHORT_DESC, "test");
//            forecastValue.put(WeatherContract.WeatherEntry.COLUMN_WEATHER_ID, "test");
//            forecastValues.add(forecastValue);
//        }
//        test.getContentResolver()
//                .bulkInsert(WeatherContract.WeatherEntry.CONTENT_URI, forecastValues.toArray(new ContentValues[forecastValues.size()]));



        SystemClock.sleep(2000);
        onView(ViewMatchers.withId(R.id.detail_day_textview)).check(matches(isDisplayed()));
    }
}
