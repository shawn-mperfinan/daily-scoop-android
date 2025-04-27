package com.dailyscoop.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dailyscoop.app.feature.onboarding.OnboardingScreen
import com.dailyscoop.app.ui.DailyscoopApp
import com.dailyscoop.app.ui.theme.DailyScoopTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainVM by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODOs: splash screen implementation will be improved once integrated with auth feature
        installSplashScreen().setKeepOnScreenCondition { viewModel.mainUiState.value.shouldKeepSplashScreen() }

        setContent {
            DailyScoopTheme {
                val mainUiState = viewModel.mainUiState.collectAsStateWithLifecycle()
                if (mainUiState.value is MainUiState.Success) {
                    val appState = mainUiState.value as MainUiState.Success
                    val isAppFirstLaunch = appState.userPreferencesData.isAppFirstLaunch

                    if (isAppFirstLaunch) {
                        OnboardingScreen { viewModel.setAppLaunched(it) }
                    } else {
                        DailyscoopApp()
                    }
                }
            }
        }
    }
}
