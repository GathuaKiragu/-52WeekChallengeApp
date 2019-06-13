package com.safaricom.hackathon;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.safaricom.hackathon.Ui.SplashScreenActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.withId;



@RunWith(AndroidJUnit4.class)
public class SplashScreenEspressoTest {
    @Rule
    public ActivityTestRule<SplashScreenActivity> mActivityRule =
            new ActivityTestRule<>(SplashScreenActivity.class);

    //Test to see if progress dialog is visible
    @Test
    public void ensureViewsAreVisible() {
        onView(withId(R.id.pdialog));
    }

}
