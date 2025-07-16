package com.raulastete.spendless.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    surface = Surface,
    onSurface = onSurface,
    background = Background,
)

private val LightColorScheme = lightColorScheme(
    primary = Primary,
    primaryContainer = PrimaryContainer,
    onPrimary = onPrimary,
    surface = Surface,
    onSurface = onSurface,
    onSurfaceVariant = onSurfaceVariant,
    background = Background,
    onBackground = OnBackgroundA08,
    error = Error,
    errorContainer = ErrorContainer
)

@Composable
fun SpendLessTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}