package edu.gatech.seclass.jobcompare6300;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;


import org.junit.runner.RunWith;


import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.isDialog;
import static androidx.test.espresso.matcher.ViewMatchers.assertThat;
import static androidx.test.espresso.matcher.ViewMatchers.hasErrorText;
import static androidx.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.anything;


import android.renderscript.Element;
import android.view.View;

@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PageLoad {

    @Rule
    public ActivityScenarioRule<MainActivity> rule = new ActivityScenarioRule<>(MainActivity.class);

//    @Before
//    public void abc() {
//
//    }

    @Test
    public void MainPage() {
        ActivityScenario scenario = rule.getScenario();
        onView(withId(R.id.btnEditCurrentJob)).check(matches(isDisplayed()));
        onView(withId(R.id.btnAddJobOffer)).check(matches(isDisplayed()));
        onView(withId(R.id.btnCompareJobs)).check(matches(isDisplayed()));
        onView(withId(R.id.currentJobHeading)).check(matches(isDisplayed()));
        onView(withId(R.id.jobOffersHeading)).check(matches(isDisplayed()));
        onView(withId(R.id.btnSettings)).check(matches(isDisplayed()));
    }

    @Test
    public void SettingsPage() {
        ActivityScenario scenario = rule.getScenario();
        onView(withId(R.id.btnSettings)).perform(click());

        onView(withId(R.id.settingHeading)).check(matches(isDisplayed()));
        onView(withId(R.id.comparisonWeightText)).check(matches(isDisplayed()));
        onView(withId(R.id.weightCancel)).check(matches(isDisplayed()));
        onView(withId(R.id.weightSave)).check(matches(isDisplayed()));
    }

    @Test
    public void EditJob() {
        ActivityScenario scenario = rule.getScenario();
        onView(withId(R.id.btnEditCurrentJob)).perform(click());

        onView(withId(R.id.editJobHeading)).check(matches(isDisplayed()));
        onView(withId(R.id.button3)).check(matches(isDisplayed()));
    }

    @Test
    public void AddCurrentJob_add() {
        Job test = new Job("abc", "abc", "abc", "AL", 80, 50000, 3000, 0, 0, 250);

        ActivityScenario scenario = rule.getScenario();
        onView(withId(R.id.btnEditCurrentJob)).perform(click());

        onView(withId(R.id.txtEditTitle)).perform(typeText(test.getTitle()));
        onView(withId(R.id.txtEditCompany)).perform(typeText(test.getCompany()));
        onView(withId(R.id.txtEditCity)).perform(typeText(test.getCity()));
        onView(withId(R.id.spinnerState)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("CA"))).perform(click());
        onView(withId(R.id.txtEditCostOfLiving)).perform(typeText(Double.toString(test.getCostOfLivingIndex())));
        onView(withId(R.id.txtEditYearlySalary)).perform(typeText(Double.toString(test.getYearlySalary())));
        onView(withId(R.id.txtEditYearlyBonus)).perform(typeText(Double.toString(test.getYearlyBonus())));
        onView(withId(R.id.txtEditWeeklyTelework)).perform(typeText(Integer.toString(test.getWeeklyTelework())));
        onView(withId(R.id.txtEditLeaveTime)).perform(typeText(Integer.toString(test.getLeaveTime())));
        onView(withId(R.id.txtEditGymAllowance)).perform(typeText(Double.toString(test.getGymAllowance())));

        onView(withId(R.id.button3)).perform(click());

        onView(withId(R.id.currentJobHeading)).check(matches(isDisplayed()));

    }

    @Test
    public void AddJobOffer() {
        ActivityScenario scenario = rule.getScenario();
        onView(withId(R.id.btnAddJobOffer)).perform(click());

        onView(withId(R.id.editJobHeading)).check(matches(isDisplayed()));
        onView(withId(R.id.editJobHeading)).check(matches(withText("Add Job Offer")));
        onView(withId(R.id.button3)).check(matches(isDisplayed()));
    }

    @Test
    public void AddJobOffer_add() {
        Job test = new Job("zxcv", "zxcv", "zxcv", "AL", 80, 50000, 3000, 0, 0, 250);

        ActivityScenario scenario = rule.getScenario();
        onView(withId(R.id.btnAddJobOffer)).perform(click());

        onView(withId(R.id.txtEditTitle)).perform(typeText(test.getTitle()));
        onView(withId(R.id.txtEditCompany)).perform(typeText(test.getCompany()));
        onView(withId(R.id.txtEditCity)).perform(typeText(test.getCity()));
        onView(withId(R.id.spinnerState)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("CA"))).perform(click());
        onView(withId(R.id.txtEditCostOfLiving)).perform(typeText(Double.toString(test.getCostOfLivingIndex())));
        onView(withId(R.id.txtEditYearlySalary)).perform(typeText(Double.toString(test.getYearlySalary())));
        onView(withId(R.id.txtEditYearlyBonus)).perform(typeText(Double.toString(test.getYearlyBonus())));
        onView(withId(R.id.txtEditWeeklyTelework)).perform(typeText(Integer.toString(test.getWeeklyTelework())));
        onView(withId(R.id.txtEditLeaveTime)).perform(typeText(Integer.toString(test.getLeaveTime())));
        onView(withId(R.id.txtEditGymAllowance)).perform(typeText(Double.toString(test.getGymAllowance())));

        onView(withId(R.id.button3)).perform(click());
        onView(withText("Job Offer Saved!")).check(matches(isDisplayed()));

    }

    @Test
    public void CompareJobs() {
        ActivityScenario scenario = rule.getScenario();
        onView(withId(R.id.btnCompareJobs)).perform(click());

        onView(withId(R.id.btn_addSnipet)).check(matches(isDisplayed()));
        onView(withId(R.id.btnReturn)).check(matches(isDisplayed()));

    }

    @Test
    public void CompareJobs_withoutSelecting() {
        ActivityScenario scenario = rule.getScenario();
        onView(withId(R.id.btnCompareJobs)).perform(click());

        onView(withId(R.id.btn_addSnipet)).perform(click());
        onView(withText("Error Message")).check(matches(isDisplayed()));
        onView(withId(android.R.id.button1)).perform(click());
    }

    @Test
    public void CompareJobs_withSelecting() throws InterruptedException {
        ActivityScenario scenario = rule.getScenario();
        onView(withId(R.id.btnCompareJobs)).perform(click());

        DataInteraction val = onData(anything());
        val.atPosition(1)
                .onChildView(withClassName(Matchers.containsString("CheckBox")))
                .perform(click());

        val.atPosition(0)
                .onChildView(withClassName(Matchers.containsString("CheckBox")))
                .perform(click());

        onView(withId(R.id.btn_addSnipet)).perform(click());
    }

}
