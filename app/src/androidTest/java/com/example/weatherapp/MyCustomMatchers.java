package com.example.weatherapp;

import android.support.annotation.NonNull;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

/**
 * Created by pivotal on 2016-01-21.
 */
public final class MyCustomMatchers {
    @NonNull
    public static Matcher<View> withNumberOfItems(final int numberOfItems) {

        return new BoundedMatcher<View, RecyclerView>(RecyclerView.class) {
//            private String resourceName = null;
//            private String expectedText = null;

            @Override
            public void describeTo(final Description description) {
//                description.appendText("with nunmber of items ");
//                description.appendValue(numberOfItems);
//                if (null != this.resourceName) {
//                    description.appendText("[");
//                    description.appendText(this.resourceName);
//                    description.appendText("]");
//                }
//                if (null != this.expectedText) {
//                    description.appendText(" value: ");
//                    description.appendText(this.expectedText);
//                }
            }

            @Override
            public boolean matchesSafely(final RecyclerView recyclerView) {
//                if (null == this.expectedText) {
//                    try {
//                        this.expectedText = String.valueOf(recyclerView.getChildCount());
////                        this.resourceName = recyclerView.getResources()
////                                .getResourceEntryName(numberOfItems);
//                    } catch (Resources.NotFoundException ignored) {
//                    /*
//                     * view could be from a context unaware of the resource
//                     * id.
//                     */
//                    }
//                }
//                if (null != this.expectedText) {
//                    return (numberOfItems == recyclerView.getChildCount());
//                } else {
//                    return false;
//                }
                return (numberOfItems == recyclerView.getAdapter().getItemCount());
            }
        };
    }
}
