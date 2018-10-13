package idwall.iddog;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.*;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.matcher.ViewMatchers.*;
import static android.support.test.espresso.assertion.ViewAssertions.matches;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.content.Intent;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import idwall.iddog.data.local.prefs.PreferencesHelper;
import idwall.iddog.ui.MainActivity;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {

    Intent intent;

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class, true, false);

    @Before
    public void setUp() {
        PreferencesHelper.setUserToken(getInstrumentation().getTargetContext(), "teste");
    }

    @Test
    public void dog_categories_isBuilded() {
        mActivityRule.launchActivity(new Intent());
        onView(withText("Hound")).perform(scrollTo(), click()).check(doesNotExist());
    }

    @After
    public void clearPreferences() {
        PreferencesHelper.setUserToken(getInstrumentation().getTargetContext(), null);
    }
}