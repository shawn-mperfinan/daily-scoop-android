package com.dailyscoop.app.utilities

import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.navOptions
import com.dailyscoop.app.feature.bookmarks.navigation.navigateToBookmarksScreen
import com.dailyscoop.app.feature.home.navigation.navigateToHomeScreen
import com.dailyscoop.app.feature.profile.navigation.navigateToProfileScreen
import com.dailyscoop.app.feature.search.navigation.navigateToNewsSearchScreen
import com.dailyscoop.app.navigation.TopLevelDestination
import com.dailyscoop.app.navigation.TopLevelDestination.BOOKMARKS
import com.dailyscoop.app.navigation.TopLevelDestination.HOME
import com.dailyscoop.app.navigation.TopLevelDestination.PROFILE
import com.dailyscoop.app.navigation.TopLevelDestination.SEARCH
import kotlin.reflect.KClass

/**
 * Checks if a given NavDestination's route is currently selected.
 *
 * @param route a [TopLevelDestination] given from the current NavController's current stack entry destination
 */
fun NavDestination?.isSelectedMainLevelDestination(route: KClass<*>): Boolean {
    return this?.hierarchy?.any { it.hasRoute(route) } == true
}

/**
 * Navigates within the available main level destinations from a given route.
 *
 * @param route a route of a given destination
 */
fun NavHostController.navigateToMainLevelDestinationRoute(route: KClass<*>) {
    val topLevelNavOptions =
        navOptions {
            // Pop up to the start destination of the graph to avoid building up a large stack of destinations
            // on the back stack as users select items
            val startDestination = this@navigateToMainLevelDestinationRoute.graph.findStartDestination().id
            popUpTo(startDestination) { saveState = true }

            // Avoid multiple copies of the same destination when re-selecting the same item
            launchSingleTop = true

            // Restore state when re-selecting a previously selected item
            restoreState = true
        }

    when (route) {
        HOME.route -> this.navigateToHomeScreen(topLevelNavOptions)
        SEARCH.route -> this.navigateToNewsSearchScreen(topLevelNavOptions)
        BOOKMARKS.route -> this.navigateToBookmarksScreen(topLevelNavOptions)
        PROFILE.route -> this.navigateToProfileScreen(topLevelNavOptions)
    }
}
