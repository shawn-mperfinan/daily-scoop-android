package com.dailyscoop.app.feature.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag

const val NEWS_SEARCH_SCREEN_TEST_TAG = "NewsSearchScreen"

@Composable
internal fun NewsSearchRouteConnector() {
    NewsSearchScreen(modifier = Modifier.fillMaxSize())
}

@Composable
private fun NewsSearchScreen(modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.testTag(NEWS_SEARCH_SCREEN_TEST_TAG),
    ) {
        Text(text = "News Search Screen")
    }
}
