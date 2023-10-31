package com.dailyscoop.app.feature.profile.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.dailyscoop.app.feature.profile.ProfileRouteConnector

const val PROFILE_ROUTE = "profile_route"

fun NavGraphBuilder.profileScreen() {
    composable(route = PROFILE_ROUTE) {
        ProfileRouteConnector()
    }
}
