package com.dailyscoop.app.feature.profile.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.dailyscoop.app.feature.profile.ProfileRouteConnector
import kotlinx.serialization.Serializable

@Serializable
object ProfileRoute // route to profile screen

fun NavController.navigateToProfileScreen(navOptions: NavOptions) = navigate(route = ProfileRoute, navOptions)

fun NavGraphBuilder.profileScreen() {
    composable<ProfileRoute> {
        ProfileRouteConnector()
    }
}
