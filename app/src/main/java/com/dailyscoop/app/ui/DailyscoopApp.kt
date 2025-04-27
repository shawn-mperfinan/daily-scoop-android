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
import androidx.navigation.compose.rememberNavController
import com.dailyscoop.app.navigation.DailyscoopNavHost
import com.dailyscoop.app.navigation.topLevelDestinations
import com.dailyscoop.app.ui.components.DailyscoopBottomBar
import com.dailyscoop.app.utilities.navigateToMainLevelDestinationRoute

@Composable
fun DailyscoopApp(navController: NavHostController = rememberNavController()) {
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStackEntry?.destination

    Scaffold(
        bottomBar = {
            DailyscoopBottomBar(
                destinations = topLevelDestinations,
                currentDestination = currentDestination,
                onNavigateToDestinationRoute = navController::navigateToMainLevelDestinationRoute,
                modifier = Modifier.fillMaxWidth(),
            )
        },
    ) { scaffoldPadding ->
        Surface(
            color = MaterialTheme.colorScheme.background,
        ) {
            DailyscoopNavHost(
                navController = navController,
                modifier = Modifier.padding(scaffoldPadding),
            )
        }
    }
}
