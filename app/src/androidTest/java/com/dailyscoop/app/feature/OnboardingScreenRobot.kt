package com.dailyscoop.app.feature

import androidx.compose.ui.test.junit4.ComposeTestRule
import com.dailyscoop.app.feature.onboarding.BACK_BUTTON_TEST_TAG
import com.dailyscoop.app.feature.onboarding.NEXT_BUTTON_TEST_TAG
import com.dailyscoop.app.feature.onboarding.ONBOARDING_PAGER_TEST_TAG
import com.dailyscoop.app.feature.onboarding.START_READING_BUTTON_TEST_TAG
import com.dailyscoop.app.robot.Robot

class OnboardingScreenRobot(composeTestRule: ComposeTestRule) : Robot(composeTestRule) {
    // -- Actions --

    fun clickNextButton() = clickByTag(NEXT_BUTTON_TEST_TAG)

    fun clickBackButton() = clickByTag(BACK_BUTTON_TEST_TAG)

    fun clickStartReadingButton() = clickByTag(START_READING_BUTTON_TEST_TAG)

    fun swipeNextOnPager() = swipeLeftByTag(ONBOARDING_PAGER_TEST_TAG)

    fun swipeBackOnPager() = swipeRightByTag(ONBOARDING_PAGER_TEST_TAG)

    // -- Assertions --

    fun verifyOnboardingItemDisplayed(
        expectedTitle: String,
        expectedDescription: String,
    ) {
        val expectedContentDescription = "$expectedTitle Image"
        assertWithText(expectedTitle)
        assertWithText(expectedDescription)
        assertWithContentDescription(expectedContentDescription)
    }

    fun verifyNextButtonDisplayed() = assertWithTag(NEXT_BUTTON_TEST_TAG)

    fun verifyBackButtonDisplayed() = assertWithTag(BACK_BUTTON_TEST_TAG)

    fun verifyStartReadingButtonDisplayed() = assertWithTag(START_READING_BUTTON_TEST_TAG)
}
