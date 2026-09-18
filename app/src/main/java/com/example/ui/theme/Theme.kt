package com.example.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColorScheme(
    primary = JapanCrimson,
    onPrimary = Color.White,
    primaryContainer = JapanCrimsonContainer,
    onPrimaryContainer = JapanOnCrimsonContainer,
    secondary = SumiCharcoal,
    onSecondary = Color.White,
    secondaryContainer = WashiSurfaceVariant,
    onSecondaryContainer = SumiInk,
    tertiary = MatchaGreen,
    onTertiary = Color.White,
    tertiaryContainer = MatchaGreenLight,
    onTertiaryContainer = Color(0xFF002204),
    background = WashiCream,
    onBackground = SumiInk,
    surface = WashiSurface,
    onSurface = SumiInk,
    surfaceVariant = WashiSurfaceVariant,
    onSurfaceVariant = SumiCharcoal,
    outline = WashiBorder,
    outlineVariant = Color(0xFFDCD6CB),
)

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFFFFB3B8),
    onPrimary = Color(0xFF680010),
    primaryContainer = Color(0xFF900820),
    onPrimaryContainer = Color(0xFFFFDAD6),
    secondary = Color(0xFFC7C5D0),
    onSecondary = Color(0xFF303038),
    secondaryContainer = Color(0xFF45464F),
    onSecondaryContainer = Color(0xFFE4E1EC),
    tertiary = Color(0xFFA5D6A7),
    onTertiary = Color(0xFF00390E),
    tertiaryContainer = Color(0xFF1B5E20),
    onTertiaryContainer = Color(0xFFC8E6C9),
    background = Color(0xFF141316),
    onBackground = Color(0xFFE6E1E5),
    surface = Color(0xFF1D1B1E),
    onSurface = Color(0xFFE6E1E5),
    surfaceVariant = Color(0xFF2B292D),
    onSurfaceVariant = Color(0xFFCDC4CE),
    outline = Color(0xFF49454E),
)

@Composable
fun NihongoTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
