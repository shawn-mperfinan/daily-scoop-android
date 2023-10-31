package com.dailyscoop.app.navigation

import com.dailyscoop.app.R
import com.dailyscoop.app.feature.bookmarks.navigation.BOOKMARKS_ROUTE
import com.dailyscoop.app.feature.home.navigation.HOME_ROUTE
import com.dailyscoop.app.feature.profile.navigation.PROFILE_ROUTE
import com.dailyscoop.app.feature.search.navigation.SEARCH_ROUTE

enum class TopLevelDestination(
    val route: String,
    val label: Int,
    val selectedIcon: Int,
    val unselectedIcon: Int
) {
    HOME(
        route = HOME_ROUTE,
        label = R.string.home_nav_label,
        selectedIcon = R.drawable.ic_home_selected,
        unselectedIcon = R.drawable.ic_home_unselected
    ),
    SEARCH(
        route = SEARCH_ROUTE,
        label = R.string.search_nav_label,
        selectedIcon = R.drawable.ic_search_selected,
        unselectedIcon = R.drawable.ic_search_unselected
    ),
    BOOKMARKS(
        route = BOOKMARKS_ROUTE,
        label = R.string.bookmark_nav_label,
        selectedIcon = R.drawable.ic_bookmarks_selected,
        unselectedIcon = R.drawable.ic_bookmarks_unselected
    ),
    PROFILE(
        route = PROFILE_ROUTE,
        label = R.string.profile_nav_label,
        selectedIcon = R.drawable.ic_profile_selected,
        unselectedIcon = R.drawable.ic_profile_unselected
    )
}

/**
 * Map of main level destinations to be used in the Bottom Bar
 */
val topLevelDestinations: List<TopLevelDestination> = TopLevelDestination.values().asList()
