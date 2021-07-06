package com.openclassrooms.entrevoisins.neighbour_detail;

import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.ui.neighbour_detail.NeighbourDetailActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.IsNull.notNullValue;

/**
 * Test class for NeighbourDetailActivity
 */

@RunWith(AndroidJUnit4.class)
public class NeighbourDetailTest {

    private NeighbourDetailActivity mActivity;

    @Rule
    public ActivityTestRule<NeighbourDetailActivity> mActivityRule =
            new ActivityTestRule(NeighbourDetailActivity.class);

    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());
    }

    /**
     * Check that the textview with neighbour’s name is populated at the start of the activity
     */
    @Test
    public void myNeighboursDetail_neighbourName_isPopulated() {
        onView(ViewMatchers.withId(R.id.activity_neighbour_detail_name_1)).check(matches(not(withText(""))));
    }
}
