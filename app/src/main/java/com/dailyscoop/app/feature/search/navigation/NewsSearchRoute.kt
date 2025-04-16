package com.dailyscoop.app.feature.search.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.dailyscoop.app.feature.search.NewsSearchRouteConnector
import kotlinx.serialization.Serializable

@Serializable
object NewsSearchRoute // route to news search screen

fun NavController.navigateToNewsSearchScreen(navOptions: NavOptions) = navigate(route = NewsSearchRoute, navOptions)

fun NavGraphBuilder.searchScreen() {
    composable<NewsSearchRoute> {
        NewsSearchRouteConnector()
    }
}
