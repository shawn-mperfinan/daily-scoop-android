package com.dailyscoop.app.feature.search.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.dailyscoop.app.feature.search.SearchRouteConnector

const val SEARCH_ROUTE = "search_route"

fun NavGraphBuilder.searchScreen() {
    composable(route = SEARCH_ROUTE) {
        SearchRouteConnector()
    }
}
