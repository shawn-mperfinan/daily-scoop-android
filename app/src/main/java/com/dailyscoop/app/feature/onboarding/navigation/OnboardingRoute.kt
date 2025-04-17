package com.dailyscoop.app.feature.onboarding.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.dailyscoop.app.feature.onboarding.OnboardingScreenConnector
import kotlinx.serialization.Serializable

@Serializable
object OnboardingRoute // route to onboarding screen

fun NavGraphBuilder.onboardingScreen() {
    composable<OnboardingRoute> {
        OnboardingScreenConnector()
    }
}
