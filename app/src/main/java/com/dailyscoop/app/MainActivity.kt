package com.dailyscoop.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.dailyscoop.app.feature.home.navigation.HomeRoute
import com.dailyscoop.app.feature.onboarding.navigation.OnboardingRoute
import com.dailyscoop.app.ui.DailyScoopApp
import com.dailyscoop.app.ui.theme.DailyScoopTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainVM by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODOs: splash screen implementation will be improved once integrated with auth feature
        installSplashScreen().setKeepOnScreenCondition {
            when (viewModel.mainUiState.value) {
                is MainUiState.Loading -> true
                is MainUiState.Success -> false
            }
        }

        setContent {
            val mainUiState = viewModel.mainUiState.collectAsStateWithLifecycle()

            when (mainUiState.value) {
                is MainUiState.Loading -> {} // Idle state, no need to show something for now
                is MainUiState.Success -> {
                    val userPreferencesState = (mainUiState.value as MainUiState.Success).userPreferencesData
                    val isAppFirstLaunch = userPreferencesState.isAppFirstLaunch

                    DailyScoopTheme {
                        val mainNavController = rememberNavController()
                        val startDestination = if (isAppFirstLaunch) OnboardingRoute::class else HomeRoute::class

                        DailyScoopApp(
                            navController = mainNavController,
                            startDestination = startDestination,
                        )
                    }
                }
            }
        }
    }
}
