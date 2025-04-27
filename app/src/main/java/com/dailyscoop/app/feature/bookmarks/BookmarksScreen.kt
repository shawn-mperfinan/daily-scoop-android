package com.dailyscoop.app.feature.bookmarks

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag

const val BOOKMARKS_SCREEN_TEST_TAG = "BookmarksScreen"

@Composable
internal fun BookmarksRouteConnector() {
    BookmarksScreen(modifier = Modifier.fillMaxSize())
}

@Composable
private fun BookmarksScreen(modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.testTag(BOOKMARKS_SCREEN_TEST_TAG),
    ) {
        Text(text = "Bookmarks Screen")
    }
}
