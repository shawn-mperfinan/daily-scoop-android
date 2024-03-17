package com.dailyscoop.app.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.dailyscoop.app.feature.onboarding.navigation.ONBOARDING_ROUTE
import com.dailyscoop.app.navigation.DailyScoopNavHost
import com.dailyscoop.app.navigation.topLevelDestinations
import com.dailyscoop.app.ui.components.DailyScoopBottomBar
import com.dailyscoop.app.utilities.navigateToMainLevelDestinationRoute

@Composable
fun DailyScoopApp(
    navController: NavHostController,
    startDestination: String,
) {
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStackEntry?.destination

    Scaffold(
        bottomBar = {
            if (startDestination != ONBOARDING_ROUTE) {
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
            color = MaterialTheme.colorScheme.background,
        ) {
            DailyScoopNavHost(
                navController = navController,
                modifier = Modifier.padding(scaffoldPadding),
                startDestination = startDestination,
            )
        }
    }
}
