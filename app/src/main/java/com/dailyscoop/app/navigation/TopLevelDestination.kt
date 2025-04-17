package com.dailyscoop.app.navigation

import androidx.annotation.StringRes
import com.dailyscoop.app.R
import com.dailyscoop.app.feature.bookmarks.navigation.BookmarksRoute
import com.dailyscoop.app.feature.home.navigation.HomeRoute
import com.dailyscoop.app.feature.profile.navigation.ProfileRoute
import com.dailyscoop.app.feature.search.navigation.NewsSearchRoute
import kotlin.reflect.KClass

enum class TopLevelDestination(
    val route: KClass<*>,
    @StringRes val label: Int,
    val selectedIcon: Int,
    val unselectedIcon: Int,
) {
    HOME(
        route = HomeRoute::class,
        label = R.string.home_nav_label,
        selectedIcon = R.drawable.ic_home_selected,
        unselectedIcon = R.drawable.ic_home_unselected,
    ),
    SEARCH(
        route = NewsSearchRoute::class,
        label = R.string.search_nav_label,
        selectedIcon = R.drawable.ic_search_selected,
        unselectedIcon = R.drawable.ic_search_unselected,
    ),
    BOOKMARKS(
        route = BookmarksRoute::class,
        label = R.string.bookmark_nav_label,
        selectedIcon = R.drawable.ic_bookmarks_selected,
        unselectedIcon = R.drawable.ic_bookmarks_unselected,
    ),
    PROFILE(
        route = ProfileRoute::class,
        label = R.string.profile_nav_label,
        selectedIcon = R.drawable.ic_profile_selected,
        unselectedIcon = R.drawable.ic_profile_unselected,
    ),
}

/**
 * Map of main level destinations to be used in the Bottom Bar
 */
val topLevelDestinations: List<TopLevelDestination> = TopLevelDestination.entries
