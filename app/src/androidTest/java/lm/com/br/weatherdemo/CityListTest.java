package lm.com.br.weatherdemo;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.ViewAssertion;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.hamcrest.CoreMatchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import lm.com.br.weatherdemo.view.CityListActivity;


import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by heitornascimento on 7/23/16.
 */
@RunWith(AndroidJUnit4.class)
public class CityListTest {

    @Rule
    public final ActivityTestRule<CityListActivity> cityListActivity =
            new ActivityTestRule<>(CityListActivity.class);


    /**
     * Check if swipe action delete city
     */
    @Test
    public void shouldDeleteCity()  {
        String tobeDeleted = "Praga";
        onView(withId(R.id.add_city)).perform(click());
        onView(withId(R.id.city_name)).perform(typeText(tobeDeleted), closeSoftKeyboard());
        onView(withText(R.string.add)).perform(click());
        onView(withText(tobeDeleted)).perform(swipeLeft());
        onView(withText(tobeDeleted)).check(doesNotExist());
    }

    /**
     * Test if the four defaults cities exist.
     */
    @Test
    public void shouldHaveTheFourCities() {
        onView(withText("Recife")).check(ViewAssertions.matches(isDisplayed()));
        onView(withText("São Paulo")).check(ViewAssertions.matches(isDisplayed()));
        onView(withText("Dublin")).check(ViewAssertions.matches(isDisplayed()));
        onView(withText("Lima")).check(ViewAssertions.matches(isDisplayed()));
    }

    /**
     * Create a new city and check if exists in list
     */
    @Test
    public void shouldCreateNewCity() {
        String newCity = "London";
        onView(withId(R.id.add_city)).perform(click());
        onView(withId(R.id.city_name)).perform(click());
        onView(withId(R.id.city_name)).perform(typeText(newCity));
        onView(withText(R.string.add)).perform(click());
        onView(withText(newCity)).check(ViewAssertions.matches(isDisplayed()));
    }

}
