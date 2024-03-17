package com.dailyscoop.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.dailyscoop.app.feature.bookmarks.navigation.bookmarksScreen
import com.dailyscoop.app.feature.home.navigation.homeScreen
import com.dailyscoop.app.feature.onboarding.navigation.onboardingScreen
import com.dailyscoop.app.feature.profile.navigation.profileScreen
import com.dailyscoop.app.feature.search.navigation.searchScreen

@Composable
fun DailyScoopNavHost(
    navController: NavHostController,
    startDestination: String,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        onboardingScreen()

        homeScreen()

        searchScreen()

        bookmarksScreen()

        profileScreen()
    }
}
