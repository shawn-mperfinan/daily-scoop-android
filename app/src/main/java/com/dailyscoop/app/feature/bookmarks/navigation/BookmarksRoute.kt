package com.dailyscoop.app.feature.bookmarks.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.dailyscoop.app.feature.bookmarks.BookmarksRouteConnector
import kotlinx.serialization.Serializable

@Serializable
object BookmarksRoute // route to bookmarks screen

fun NavController.navigateToBookmarksScreen(navOptions: NavOptions) = navigate(route = BookmarksRoute, navOptions)

fun NavGraphBuilder.bookmarksScreen() {
    composable<BookmarksRoute> {
        BookmarksRouteConnector()
    }
}
