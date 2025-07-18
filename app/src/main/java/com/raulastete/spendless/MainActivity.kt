package com.raulastete.spendless

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.raulastete.spendless.screen.settings.preferences.PreferencesScreen
import com.raulastete.spendless.ui.theme.SpendLessTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge()
        setContent {
            SpendLessTheme {
                PreferencesScreen()
            }
        }
    }
}