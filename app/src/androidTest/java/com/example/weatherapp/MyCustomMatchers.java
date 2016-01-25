package com.example.weatherapp;

import android.support.annotation.NonNull;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

import java.util.Collection;

/**
 * Created by pivotal on 2016-01-21.
 */
public final class MyCustomMatchers {
    @NonNull
    public static Matcher<View> withNumberofItems(final Matcher<Collection<?>> sizeMatcher) {

        return new BoundedMatcher<View, RecyclerView>(RecyclerView.class) {

            @Override
            public void describeTo(final Description description) {
//                description.appendText("with error text: ");
//                stringMatcher.describeTo(description);

            }

            @Override
            public boolean matchesSafely(final RecyclerView recyclerView) {
                return sizeMatcher.matches(recyclerView.getScrollBarSize());
            }
        };
    }
}
