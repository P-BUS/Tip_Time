package com.example.tiptime

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.Matchers.containsString
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CalculatorTests {

    @get:Rule()
    val activity = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun calculate_10_percent_tip() {
        onView(withId(R.id.cost_of_service_edit_text))
            .perform(typeText("203.00"))
            .perform(ViewActions.closeSoftKeyboard())

        onView(withId(R.id.great_button))
            .perform(click())

        onView(withId(R.id.calculate_tips_button))
            .perform(click())

        onView(withId(R.id.tip_result))
            .check(matches(withText(containsString("$21.00"))))

        onView(withId(R.id.switch_share_tips))
            .perform(click())

        onView(withId(R.id.calculate_tips_button))
            .perform(click())

        onView(withId(R.id.tip_result))
            .check(matches(withText(containsString("$20.30"))))
    }
    @Test
    fun calculate_5_percent_tip() {
        onView(withId(R.id.cost_of_service_edit_text))
            .perform(typeText("203.00"))
            .perform(ViewActions.closeSoftKeyboard())

        onView(withId(R.id.as_usual_button))
            .perform(click())

        onView(withId(R.id.calculate_tips_button))
            .perform(click())

        onView(withId(R.id.tip_result))
            .check(matches(withText(containsString("$11.00"))))

        onView(withId(R.id.switch_share_tips))
            .perform(click())

        onView(withId(R.id.calculate_tips_button))
            .perform(click())

        onView(withId(R.id.tip_result))
            .check(matches(withText(containsString("$10.15"))))
    }
    @Test
    fun calculate_0_percent_tip() {
        onView(withId(R.id.cost_of_service_edit_text))
            .perform(typeText("300.00"))
            .perform(ViewActions.closeSoftKeyboard())

        onView(withId(R.id.not_good_button))
            .perform(click())

        onView(withId(R.id.calculate_tips_button))
            .perform(click())

        onView(withId(R.id.tip_result))
            .check(matches(withText(containsString("0"))))

        onView(withId(R.id.switch_share_tips))
            .perform(click())

        onView(withId(R.id.calculate_tips_button))
            .perform(click())

        onView(withId(R.id.tip_result))
            .check(matches(withText(containsString("0"))))
    }
}

