package com.dailyscoop.app.feature

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createComposeRule
import com.dailyscoop.app.R
import com.dailyscoop.app.feature.onboarding.OnboardingScreen
import com.dailyscoop.app.ui.theme.DailyScoopTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class OnboardingScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var onboardingRobot: OnboardingScreenRobot

    private lateinit var fakeOnboardingScreenItems: List<Pair<String, String>>

    private var startReadingClicked: Boolean = false

    @Before
    fun setupOnboardingScreen() {
        composeTestRule.setContent {
            val context = LocalContext.current

            fakeOnboardingScreenItems =
                listOf(
                    Pair(
                        context.getString(R.string.home_onboarding_label),
                        context.getString(R.string.home_onboarding_description),
                    ),
                    Pair(
                        context.getString(R.string.search_onboarding_label),
                        context.getString(R.string.search_onboarding_description),
                    ),
                    Pair(
                        context.getString(R.string.bookmark_onboarding_label),
                        context.getString(R.string.bookmark_onboarding_description),
                    ),
                )
            DailyScoopTheme {
                OnboardingScreen(onStartReading = { startReadingClicked = true })
            }
        }

        onboardingRobot = OnboardingScreenRobot(composeTestRule)
    }

    // --- Screen Entry Test ---

    @Test
    fun verifyFirstOnboardingItemDisplayed_whenScreenIsEntered() {
        with(onboardingRobot) {
            val (expectedTitle, expectedDescription) = fakeOnboardingScreenItems.first()
            verifyOnboardingItemDisplayed(expectedTitle, expectedDescription)
        }
    }

    // --- Button Visibility Tests ---

    @Test
    fun verifyNextButtonDisplayed_whenScreenIsEntered() {
        onboardingRobot.verifyNextButtonDisplayed()
    }

    @Test
    fun verifyNextAndBackButtonsDisplayed_whenSwipedToSecondOnboardingItem() {
        with(onboardingRobot) {
            swipeNextOnPager()
            verifyNextButtonDisplayed()
            verifyBackButtonDisplayed()
        }
    }

    @Test
    fun verifyStartReadingButtonsDisplayed_whenSwipedToLastOnboardingItem() {
        with(onboardingRobot) {
            repeat(fakeOnboardingScreenItems.lastIndex) {
                swipeNextOnPager()
            }
            verifyStartReadingButtonDisplayed()
        }
    }

    @Test
    fun verifyStartReadingButtonsDisplayed_whenNextButtonClickedToLastOnboardingItem() {
        with(onboardingRobot) {
            repeat(fakeOnboardingScreenItems.lastIndex) {
                clickNextButton()
            }
            verifyStartReadingButtonDisplayed()
        }
    }

    // --- Swiping Behavior Tests ---

    @Test
    fun verifySecondOnboardingItemDisplayed_whenSwipedLeftFromFirstItem() {
        with(onboardingRobot) {
            val (expectedTitle, expectedDescription) = fakeOnboardingScreenItems[1]

            swipeNextOnPager()
            verifyOnboardingItemDisplayed(expectedTitle, expectedDescription)
        }
    }

    @Test
    fun verifyThirdOnboardingItemDisplayed_whenSwipedToLastItem() {
        with(onboardingRobot) {
            val (expectedTitle, expectedDescription) = fakeOnboardingScreenItems.last()

            repeat(fakeOnboardingScreenItems.lastIndex) {
                swipeNextOnPager()
            }
            verifyOnboardingItemDisplayed(expectedTitle, expectedDescription)
        }
    }

    @Test
    fun verifyViewPagerScrollingDisabled_whenThirdOnboardingItemDisplayed() {
        with(onboardingRobot) {
            val (expectedTitle, expectedDescription) = fakeOnboardingScreenItems.last()

            repeat(fakeOnboardingScreenItems.lastIndex) {
                swipeNextOnPager()
            }
            swipeBackOnPager()
            verifyOnboardingItemDisplayed(expectedTitle, expectedDescription)
        }
    }

    // --- Button Behavior Tests ---

    @Test
    fun verifySecondOnboardingItemDisplayed_whenNextButtonClickedFromFirst() {
        with(onboardingRobot) {
            val (expectedTitle, expectedDescription) = fakeOnboardingScreenItems[1]

            clickNextButton()
            verifyOnboardingItemDisplayed(expectedTitle, expectedDescription)
        }
    }

    @Test
    fun verifyFirstOnboardingItemDisplayed_whenBackButtonClickedFromSecond() {
        with(onboardingRobot) {
            val (expectedTitle, expectedDescription) = fakeOnboardingScreenItems[0]

            clickNextButton()
            clickBackButton()
            verifyOnboardingItemDisplayed(expectedTitle, expectedDescription)
        }
    }

    @Test
    fun verifyThirdOnboardingItemDisplayed_whenNextButtonClickedFromSecond() {
        with(onboardingRobot) {
            val (expectedTitle, expectedDescription) = fakeOnboardingScreenItems.last()

            clickNextButton()
            clickNextButton()
            verifyOnboardingItemDisplayed(expectedTitle, expectedDescription)
        }
    }

    @Test
    fun verifyOnClickedTriggered_whenStartReadingButtonClicked() {
        with(onboardingRobot) {
            clickNextButton()
            clickNextButton()
            clickStartReadingButton()
            assert(startReadingClicked == true) // Check if button is really clicked
        }
    }
}
