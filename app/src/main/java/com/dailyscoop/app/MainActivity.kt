package com.dailyscoop.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.dailyscoop.app.feature.home.navigation.HOME_ROUTE
import com.dailyscoop.app.feature.onboarding.navigation.ONBOARDING_ROUTE
import com.dailyscoop.app.ui.DailyScoopApp
import com.dailyscoop.app.ui.theme.DailyScoopTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainVM by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODOs: splash screen implementation will be improved once integrated with auth feature
        installSplashScreen().apply {
            setKeepOnScreenCondition(condition = {
                viewModel.shouldShowSplashScreen.value
            })
        }

        setContent {
            val isAppFirstLaunch = viewModel.isAppFirstLaunch.collectAsStateWithLifecycle()

            DailyScoopTheme {
                val mainNavController = rememberNavController()
                val appEntryStartDestination = if (isAppFirstLaunch.value) ONBOARDING_ROUTE else HOME_ROUTE

                DailyScoopApp(
                    navController = mainNavController,
                    startDestination = appEntryStartDestination,
                )
            }
        }
    }
}
