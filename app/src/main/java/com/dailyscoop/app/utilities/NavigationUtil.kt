package com.dailyscoop.app.utilities

import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.navOptions
import com.dailyscoop.app.navigation.TopLevelDestination

/**
 * Checks if a given NavDestination's route is currently selected.
 *
 * @param destination a [TopLevelDestination] given from the current NavController's current stack entry destination
 */
fun NavDestination?.isSelectedMainLevelDestination(destination: TopLevelDestination): Boolean {
    val isUnderNavDestinationHierarchy = this?.hierarchy?.any { navDestination ->
        navDestination.route?.contains(destination.name, true) ?: false
    }

    return isUnderNavDestinationHierarchy ?: false
}

/**
 * Navigates within the available main level destinations from a given route.
 *
 * @param route a route of a given destination
 */
fun NavHostController.navigateToMainLevelDestinationRoute(route: String) {
    val navOptions = navOptions {
        // Pop up to the start destination of the graph to avoid building up a large stack of destinations
        // on the back stack as users select items
        val startDestination = this@navigateToMainLevelDestinationRoute.graph.findStartDestination().id
        popUpTo(startDestination) { saveState = true }

        // Avoid multiple copies of the same destination when re-selecting the same item
        launchSingleTop = true

        // Restore state when re-selecting a previously selected item
        restoreState = true
    }

    this.navigate(route, navOptions)
}
