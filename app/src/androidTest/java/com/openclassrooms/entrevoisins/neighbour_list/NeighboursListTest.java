
package com.openclassrooms.entrevoisins.neighbour_list;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;
import com.openclassrooms.entrevoisins.utils.DeleteViewAction;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.pressBack;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static com.openclassrooms.entrevoisins.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.IsNull.notNullValue;



/**
 * Test class for list of neighbours
 */
@RunWith(AndroidJUnit4.class)
public class NeighboursListTest {

    // This is fixed
    private static int ITEMS_COUNT = 12;
    private static int FAVORITES_COUNT = 3;

    private ListNeighbourActivity mActivity;

    @Rule
    public ActivityTestRule<ListNeighbourActivity> mActivityRule =
            new ActivityTestRule(ListNeighbourActivity.class);

    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());
    }

    /**
     * We ensure that our recyclerview is displaying at least on item
     */
    @Test
    public void myNeighboursList_shouldNotBeEmpty() {
        // First scroll to the position that needs to be matched and click on it.
        onView(ViewMatchers.withId(R.id.list_neighbours))
                .check(matches(hasMinimumChildCount(1)));
    }

    /**
     * When we delete an item, the item is no more shown
     */
    @Test
    public void myNeighboursList_deleteAction_shouldRemoveItem() {
        // Given : We remove the element at position 2
        onView(ViewMatchers.withId(R.id.list_neighbours)).check(withItemCount(ITEMS_COUNT));
        // When perform a click on a delete icon
        onView(ViewMatchers.withId(R.id.list_neighbours))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));
        // Then : the number of element is 11
        onView(ViewMatchers.withId(R.id.list_neighbours)).check(withItemCount(ITEMS_COUNT-1));
    }

    /**
     * When we click on an item, the details activity is shown
     */
    @Test
    public void myNeighboursList_clickAction_shouldDisplayDetailActivity() {
        onView(ViewMatchers.withId(R.id.list_neighbours)).perform(click());
        onView(ViewMatchers.withId(R.id.activity_neighbour_detail_picture)).check(matches(isDisplayed()));
    }

    /**
     * Checks that the favorites tab only displays neighbours marked as favorites
     */
    @Test
    public void myFavoritesList_onlyDisplaysFavorites() {
        onView(ViewMatchers.withId(R.id.list_favorites)).check(withItemCount(FAVORITES_COUNT));
        onView(ViewMatchers.withId(R.id.list_favorites)).check(matches(hasDescendant(ViewMatchers.withText("Caroline"))));
        onView(ViewMatchers.withId(R.id.list_favorites)).check(matches(hasDescendant(ViewMatchers.withText("Dan"))));
        onView(ViewMatchers.withId(R.id.list_favorites)).check(matches(hasDescendant(ViewMatchers.withText("Vincent"))));
        onView(ViewMatchers.withId(R.id.list_favorites)).check(matches(not(hasDescendant(ViewMatchers.withText("Chloé")))));
        onView(ViewMatchers.withId(R.id.list_favorites)).check(matches(not(hasDescendant(ViewMatchers.withText("Patrick")))));
    }

    /**
     * Checks that clicking you can add a favorite by clicking on the button on the detailActivity
     */
    @Test
    public void favoriteActionButton_addsAFavorite() {
        // Given : we check the favorites list item count
        onView(ViewMatchers.withId(R.id.list_favorites)).check(withItemCount(FAVORITES_COUNT));
        // When : we click on the Favorite Action Button
        onView(ViewMatchers.withId(R.id.list_neighbours)).perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));
        onView(ViewMatchers.withId(R.id.favoriteActionButton)).perform(click());
        onView(ViewMatchers.isRoot()).perform(pressBack());
        // Then : the favorites list has one more element
        onView(ViewMatchers.withId(R.id.list_favorites)).check(withItemCount(FAVORITES_COUNT+1));
    }
}