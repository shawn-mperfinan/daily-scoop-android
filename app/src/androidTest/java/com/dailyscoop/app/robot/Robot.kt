package com.dailyscoop.app.robot

import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.swipeLeft

/**
 * A reusable Robot class to interact with Jetpack Compose UI elements during testing.
 *
 * @param composeTestRule The ComposeTestRule instance used for UI testing.
 */
abstract class Robot(val composeTestRule: ComposeTestRule) {
    // -- Actions --

    /**
     * Clicks a UI element identified by its [testTag]. The element must be displayed and have a click action.
     *
     * @param testTag The test tag identifying the UI element to interact with.
     */
    fun clickByTag(testTag: String) =
        composeTestRule.onNodeWithTag(testTag)
            .assertIsDisplayed()
            .assertHasClickAction()
            .performClick()

    /**
     * Swipes left on a UI element identified by its [testTag]. The element must be displayed and interactable.
     *
     * @param testTag The test tag identifying the UI element to interact with.
     */
    fun swipeLeftByTag(testTag: String) =
        composeTestRule.onNodeWithTag(testTag)
            .performTouchInput { swipeLeft() }

    /**
     * Swipes right on a UI element identified by its [testTag]. The element must be displayed and interactable.
     *
     * @param testTag The test tag identifying the UI element to interact with.
     */
    fun swipeRightByTag(testTag: String) =
        composeTestRule.onNodeWithTag(testTag)
            .performTouchInput { swipeLeft() }

    // -- Assertions --

    /**
     * Asserts that a UI element identified by its [testTag] is displayed on the screen.
     *
     * @param testTag The test tag identifying the UI element to check.
     */
    fun assertWithTag(testTag: String) =
        composeTestRule.onNodeWithTag(testTag)
            .assertIsDisplayed()

    /**
     * Asserts that a UI element containing the given [text] is displayed on the screen.
     *
     * @param text The text to match for the UI element.
     */
    fun assertWithText(text: String) =
        composeTestRule.onNodeWithText(text)
            .assertIsDisplayed()

    /**
     * Asserts that a UI element with the given `contentDescription` is displayed on the screen.
     *
     * @param description The content description to match for the UI element.
     */
    fun assertWithContentDescription(description: String) =
        composeTestRule.onNodeWithContentDescription(description)
            .assertIsDisplayed()
}
