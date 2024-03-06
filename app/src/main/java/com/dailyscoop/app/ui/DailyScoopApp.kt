package com.dailyscoop.app.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.dailyscoop.app.feature.home.navigation.HOME_ROUTE
import com.dailyscoop.app.feature.onboarding.navigation.ONBOARDING_ROUTE
import com.dailyscoop.app.navigation.DailyScoopNavHost
import com.dailyscoop.app.navigation.topLevelDestinations
import com.dailyscoop.app.ui.components.DailyScoopBottomBar
import com.dailyscoop.app.utilities.navigateToMainLevelDestinationRoute

@Composable
fun DailyScoopApp() {
    val navController = rememberNavController()
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStackEntry?.destination

    // TODO - remove this when working for data layer integration needed for onboarding screen feature
    val isAppFirstUse = true

    Scaffold(
        bottomBar = {
            if (!isAppFirstUse) {
                DailyScoopBottomBar(
                    destinations = topLevelDestinations,
                    currentDestination = currentDestination,
                    onNavigateToDestinationRoute = navController::navigateToMainLevelDestinationRoute,
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        },
    ) { scaffoldPadding ->
        Surface(
            modifier = Modifier,
            color = MaterialTheme.colorScheme.background,
        ) {
            DailyScoopNavHost(
                navController = navController,
                modifier = Modifier.padding(scaffoldPadding),
                startDestination = if (isAppFirstUse) ONBOARDING_ROUTE else HOME_ROUTE,
            )
        }
    }
}
