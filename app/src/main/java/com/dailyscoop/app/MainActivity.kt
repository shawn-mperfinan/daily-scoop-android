package com.dailyscoop.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.dailyscoop.app.ui.DailyScoopApp
import com.dailyscoop.app.ui.theme.DailyScoopTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODOs: splash screen implementation will be improved once integrated with auth feature
        installSplashScreen()

        setContent {
            DailyScoopTheme {
                DailyScoopApp()
            }
        }
    }
}
