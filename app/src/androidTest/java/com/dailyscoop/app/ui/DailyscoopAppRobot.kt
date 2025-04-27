package com.dailyscoop.app.ui

import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.navigation.testing.TestNavHostController
import com.dailyscoop.app.feature.bookmarks.BOOKMARKS_SCREEN_TEST_TAG
import com.dailyscoop.app.feature.home.HOME_SCREEN_TEST_TAG
import com.dailyscoop.app.feature.profile.PROFILE_SCREEN_TEST_TAG
import com.dailyscoop.app.feature.search.NEWS_SEARCH_SCREEN_TEST_TAG
import com.dailyscoop.app.navigation.BOOKMARKS_NAV_ITEM_TEST_TAG
import com.dailyscoop.app.navigation.HOME_NAV_ITEM_TEST_TAG
import com.dailyscoop.app.navigation.NEWS_SEARCH_NAV_ITEM_TEST_TAG
import com.dailyscoop.app.navigation.PROFILE_NAV_ITEM_TEST_TAG
import com.dailyscoop.app.robot.Robot
import com.dailyscoop.app.ui.components.BOTTOM_BAR_TEST_TAG

class DailyscoopAppRobot(composeTestRule: ComposeTestRule) : Robot(composeTestRule) {
    // -- Actions --

    fun clickHomeNavItem() = clickByTag(HOME_NAV_ITEM_TEST_TAG)

    fun clickNewsSearchNavItem() = clickByTag(NEWS_SEARCH_NAV_ITEM_TEST_TAG)

    fun clickBookmarksNavItem() = clickByTag(BOOKMARKS_NAV_ITEM_TEST_TAG)

    fun clickProfileNavItem() = clickByTag(PROFILE_NAV_ITEM_TEST_TAG)

    fun popBackStack(navController: TestNavHostController) =
        composeTestRule.runOnUiThread {
            navController.popBackStack()
        }

    // -- Assertions --

    fun verifyBottomBarIsDisplayed() {
        assertWithTag(BOTTOM_BAR_TEST_TAG)

        // Ensure all nav items are displayed properly as well
        assertWithTag(HOME_NAV_ITEM_TEST_TAG)
        assertWithTag(NEWS_SEARCH_NAV_ITEM_TEST_TAG)
        assertWithTag(BOOKMARKS_NAV_ITEM_TEST_TAG)
        assertWithTag(PROFILE_NAV_ITEM_TEST_TAG)
    }

    fun verifyHomeScreenIsDisplayed() = assertWithTag(HOME_SCREEN_TEST_TAG)

    fun verifyNewsSearchScreenIsDisplayed() = assertWithTag(NEWS_SEARCH_SCREEN_TEST_TAG)

    fun verifyBookmarksScreenIsDisplayed() = assertWithTag(BOOKMARKS_SCREEN_TEST_TAG)

    fun verifyProfileScreenIsDisplayed() = assertWithTag(PROFILE_SCREEN_TEST_TAG)
}
