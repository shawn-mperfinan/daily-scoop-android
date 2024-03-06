package com.dailyscoop.app.feature.onboarding.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.dailyscoop.app.feature.onboarding.OnboardingScreenConnector

const val ONBOARDING_ROUTE = "onboarding_route"

fun NavGraphBuilder.onboardingScreen() {
    composable(route = ONBOARDING_ROUTE) {
        OnboardingScreenConnector()
    }
}
