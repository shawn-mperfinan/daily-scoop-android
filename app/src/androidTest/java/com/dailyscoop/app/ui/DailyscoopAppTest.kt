package com.dailyscoop.app.ui

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.dailyscoop.app.ui.theme.DailyScoopTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DailyscoopAppTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var dailyscoopRobot: DailyscoopAppRobot

    private lateinit var navHostController: TestNavHostController

    @Before
    fun setupDailyscoopApp() {
        composeTestRule.setContent {
            navHostController =
                TestNavHostController(LocalContext.current).apply {
                    navigatorProvider.addNavigator(ComposeNavigator())
                }

            DailyScoopTheme {
                DailyscoopApp(navController = navHostController)
            }
        }

        dailyscoopRobot = DailyscoopAppRobot(composeTestRule)
    }

    @Test
    fun verifyBottomBarIsDisplayed_whenScreenIsEntered() {
        dailyscoopRobot.verifyBottomBarIsDisplayed()
    }

    @Test
    fun verifyHomeScreenAsStartDestination_whenScreenIsEntered() {
        dailyscoopRobot.verifyHomeScreenIsDisplayed()
    }

    @Test
    fun verifyNewsSearchScreenIsDisplayed_whenNavigatedToNewsSearch() {
        with(dailyscoopRobot) {
            clickNewsSearchNavItem()
            verifyNewsSearchScreenIsDisplayed()
        }
    }

    @Test
    fun verifyBookmarksScreenIsDisplayed_whenNavigatedToBookmarks() {
        with(dailyscoopRobot) {
            clickBookmarksNavItem()
            verifyBookmarksScreenIsDisplayed()
        }
    }

    @Test
    fun verifyProfileScreenIsDisplayed_whenNavigatedToProfile() {
        with(dailyscoopRobot) {
            clickProfileNavItem()
            verifyProfileScreenIsDisplayed()
        }
    }

    @Test
    fun verifyHomeScreenIsDisplayed_whenNavigatedToHome() {
        with(dailyscoopRobot) {
            clickProfileNavItem()
            clickHomeNavItem()
            verifyHomeScreenIsDisplayed()
        }
    }

    @Test
    fun verifyHomeScreenIsDisplayed_whenPoppedBackToHome() {
        with(dailyscoopRobot) {
            clickProfileNavItem()
            verifyProfileScreenIsDisplayed()

            popBackStack(navHostController)
            verifyHomeScreenIsDisplayed()
        }
    }

    @Test
    fun verifyAppDoesNotCrash_whenSelectedItemIsReselected() {
        with(dailyscoopRobot) {
            clickHomeNavItem()
            clickHomeNavItem()
            verifyHomeScreenIsDisplayed()
        }
    }

    @Test
    fun verifyAppDoesNotCrash_whenRapidlyClicked() {
        with(dailyscoopRobot) {
            clickNewsSearchNavItem()
            clickBookmarksNavItem()
            clickProfileNavItem()
            verifyProfileScreenIsDisplayed()
        }
    }
}
