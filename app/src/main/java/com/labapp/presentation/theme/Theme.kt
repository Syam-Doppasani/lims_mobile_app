package com.labapp.presentation.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = TealPrimary,
    secondary = TealSecondary,
    tertiary = TealTertiary,
    background = DarkBg,
    surface = DarkSurface,
    onBackground = DarkOnBg,
    onSurface = DarkOnSurface,
    outline = DarkOutline,
    error = ErrorRed
)

private val LightColorScheme = lightColorScheme(
    primary = TealPrimary,
    secondary = TealSecondary,
    tertiary = TealTertiary,
    background = LightBg,
    surface = LightSurface,
    onBackground = LightOnBg,
    onSurface = LightOnSurface,
    outline = LightOutline,
    error = ErrorRed
)

@Composable
fun LabAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val activity = view.context as? Activity
            activity?.let {
                val window = it.window
                window.statusBarColor = colorScheme.background.toArgb()
                WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
            }
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
