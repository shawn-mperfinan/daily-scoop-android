package com.dailyscoop.app.feature.home.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.dailyscoop.app.feature.home.HomeRouteConnector

const val HOME_ROUTE = "home_route"

fun NavGraphBuilder.homeScreen() {
    composable(route = HOME_ROUTE) {
        HomeRouteConnector()
    }
}
