package com.dailyscoop.app.feature.bookmarks.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.dailyscoop.app.feature.bookmarks.BookmarksRouteConnector

const val BOOKMARKS_ROUTE = "bookmarks_route"

fun NavGraphBuilder.bookmarksScreen() {
    composable(route = BOOKMARKS_ROUTE) {
        BookmarksRouteConnector()
    }
}
