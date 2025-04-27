package com.dailyscoop.app.feature.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp

const val HOME_SCREEN_TEST_TAG = "HomeScreen"

@Composable
internal fun HomeRouteConnector() {
    // TODOs:  Inject VM here once finalized
    HomeScreen(modifier = Modifier.fillMaxSize())
}

@Composable
private fun HomeScreen(modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier.testTag(HOME_SCREEN_TEST_TAG)) {
        items(SAMPLE_ITEMS) {
            Text(
                text = "News Item: $it",
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
            )
        }
    }
}

const val SAMPLE_ITEMS = 50
