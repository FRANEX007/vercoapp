package com.example.nicolas.vercoapp.activities;


import android.support.test.espresso.DataInteraction;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.example.nicolas.vercoapp.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.pressImeActionButton;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;


//buscar, filtrar, mostrar texto hombre, mujer, mostrar todos.

@LargeTest
@RunWith(AndroidJUnit4.class)
public class SearchProductActivityTest5 {

    @Rule
    public ActivityTestRule<SearchProductActivity> mActivityTestRule = new ActivityTestRule<>(SearchProductActivity.class);

    @Test
    public void searchProductActivityTest5() {

        ViewInteraction appCompatImageView = onView(
                allOf(withClassName(is("android.support.v7.widget.AppCompatImageView")), withContentDescription("Buscar"),
                        childAtPosition(
                                allOf(withClassName(is("android.widget.LinearLayout")),
                                        childAtPosition(
                                                withId(R.id.searchView),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatImageView.perform(click());

        ViewInteraction searchAutoComplete = onView(
                allOf(withClassName(is("android.widget.SearchView$SearchAutoComplete")),
                        childAtPosition(
                                allOf(withClassName(is("android.widget.LinearLayout")),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                1)),
                                0),
                        isDisplayed()));
        searchAutoComplete.perform(replaceText("zapatillas"), closeSoftKeyboard());

        ViewInteraction searchAutoComplete2 = onView(
                allOf(withClassName(is("android.widget.SearchView$SearchAutoComplete")), withText("zapatillas"),
                        childAtPosition(
                                allOf(withClassName(is("android.widget.LinearLayout")),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                1)),
                                0),
                        isDisplayed()));
        searchAutoComplete2.perform(pressImeActionButton());


        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.filterButton), withText("FILTRAR"),
                        childAtPosition(
                                allOf(withId(R.id.filtersLinearLayout),
                                        childAtPosition(
                                                withClassName(is("android.widget.RelativeLayout")),
                                                1)),
                                0),
                        isDisplayed()));
        appCompatButton.perform(click());

        DataInteraction appCompatCheckedTextView = onData(anything())
                .inAdapterView(allOf(withClassName(is("com.android.internal.app.AlertController$RecycleListView")),
                        childAtPosition(
                                withClassName(is("android.widget.FrameLayout")),
                                0)))
                .atPosition(0);
        appCompatCheckedTextView.perform(click());

        ViewInteraction textView = onView(
                allOf(withId(R.id.selectedTypeTextView), withText("HOMBRE"),
                        childAtPosition(
                                allOf(withId(R.id.filtersLinearLayout),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.widget.RelativeLayout.class),
                                                1)),
                                1),
                        isDisplayed()));
        textView.check(matches(withText("HOMBRE")));


        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.filterButton), withText("FILTRAR"),
                        childAtPosition(
                                allOf(withId(R.id.filtersLinearLayout),
                                        childAtPosition(
                                                withClassName(is("android.widget.RelativeLayout")),
                                                1)),
                                0),
                        isDisplayed()));
        appCompatButton2.perform(click());

        DataInteraction appCompatCheckedTextView2 = onData(anything())
                .inAdapterView(allOf(withClassName(is("com.android.internal.app.AlertController$RecycleListView")),
                        childAtPosition(
                                withClassName(is("android.widget.FrameLayout")),
                                0)))
                .atPosition(1);
        appCompatCheckedTextView2.perform(click());

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.selectedTypeTextView), withText("MUJER"),
                        childAtPosition(
                                allOf(withId(R.id.filtersLinearLayout),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.widget.RelativeLayout.class),
                                                1)),
                                1),
                        isDisplayed()));
        textView3.check(matches(withText("MUJER")));

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.filterButton), withText("FILTRAR"),
                        childAtPosition(
                                allOf(withId(R.id.filtersLinearLayout),
                                        childAtPosition(
                                                withClassName(is("android.widget.RelativeLayout")),
                                                1)),
                                0),
                        isDisplayed()));
        appCompatButton3.perform(click());

        DataInteraction appCompatCheckedTextView3 = onData(anything())
                .inAdapterView(allOf(withClassName(is("com.android.internal.app.AlertController$RecycleListView")),
                        childAtPosition(
                                withClassName(is("android.widget.FrameLayout")),
                                0)))
                .atPosition(2);
        appCompatCheckedTextView3.perform(click());

        ViewInteraction textView4 = onView(
                allOf(withId(R.id.selectedTypeTextView), withText("MOSTRAR TODO"),
                        childAtPosition(
                                allOf(withId(R.id.filtersLinearLayout),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.widget.RelativeLayout.class),
                                                1)),
                                1),
                        isDisplayed()));
        textView4.check(matches(withText("MOSTRAR TODO")));

    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
