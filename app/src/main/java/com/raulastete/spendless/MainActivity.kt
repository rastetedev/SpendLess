package com.raulastete.spendless

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.raulastete.spendless.screen.settings.preferences.PreferencesScreen
import com.raulastete.spendless.screen.settings.root.SettingsRootScreen
import com.raulastete.spendless.screen.settings.security.SecurityScreen
import com.raulastete.spendless.ui.theme.SpendLessTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SpendLessTheme {
                PreferencesScreen()
            }
        }
    }
}