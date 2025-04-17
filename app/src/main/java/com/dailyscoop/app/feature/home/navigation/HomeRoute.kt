package com.dailyscoop.app.feature.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.dailyscoop.app.feature.home.HomeRouteConnector
import kotlinx.serialization.Serializable

@Serializable
object HomeRoute // route to home screen

fun NavController.navigateToHomeScreen(navOptions: NavOptions) = navigate(route = HomeRoute, navOptions)

fun NavGraphBuilder.homeScreen() {
    composable<HomeRoute> {
        HomeRouteConnector()
    }
}
