package com.dailyscoop.app.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.dailyscoop.app.R
import com.dailyscoop.app.feature.bookmarks.navigation.BookmarksRoute
import com.dailyscoop.app.feature.home.navigation.HomeRoute
import com.dailyscoop.app.feature.profile.navigation.ProfileRoute
import com.dailyscoop.app.feature.search.navigation.NewsSearchRoute
import kotlin.reflect.KClass

const val HOME_NAV_ITEM_TEST_TAG = "HomeDestination"
const val NEWS_SEARCH_NAV_ITEM_TEST_TAG = "SearchDestination"
const val BOOKMARKS_NAV_ITEM_TEST_TAG = "BookmarksDestination"
const val PROFILE_NAV_ITEM_TEST_TAG = "ProfileDestination"

enum class TopLevelDestination(
    val route: KClass<*>,
    @StringRes val label: Int,
    @DrawableRes val selectedIcon: Int,
    @DrawableRes val unselectedIcon: Int,
    val testTag: String,
) {
    HOME(
        route = HomeRoute::class,
        label = R.string.home_nav_label,
        selectedIcon = R.drawable.ic_home_selected,
        unselectedIcon = R.drawable.ic_home_unselected,
        testTag = HOME_NAV_ITEM_TEST_TAG,
    ),
    SEARCH(
        route = NewsSearchRoute::class,
        label = R.string.search_nav_label,
        selectedIcon = R.drawable.ic_search_selected,
        unselectedIcon = R.drawable.ic_search_unselected,
        testTag = NEWS_SEARCH_NAV_ITEM_TEST_TAG,
    ),
    BOOKMARKS(
        route = BookmarksRoute::class,
        label = R.string.bookmark_nav_label,
        selectedIcon = R.drawable.ic_bookmarks_selected,
        unselectedIcon = R.drawable.ic_bookmarks_unselected,
        testTag = BOOKMARKS_NAV_ITEM_TEST_TAG,
    ),
    PROFILE(
        route = ProfileRoute::class,
        label = R.string.profile_nav_label,
        selectedIcon = R.drawable.ic_profile_selected,
        unselectedIcon = R.drawable.ic_profile_unselected,
        testTag = PROFILE_NAV_ITEM_TEST_TAG,
    ),
}

/**
 * Map of main level destinations to be used in the Bottom Bar
 */
val topLevelDestinations: List<TopLevelDestination> = TopLevelDestination.entries
