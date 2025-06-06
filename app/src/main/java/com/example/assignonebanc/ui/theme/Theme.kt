package com.example.assignonebanc.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

// Material 3 Expressive Color Scheme
private val ExpressiveDarkColorScheme = darkColorScheme(
    primary = Color(0xFFFFB3BA), // Soft pink
    onPrimary = Color(0xFF2D1B1F),
    primaryContainer = Color(0xFF452F34),
    onPrimaryContainer = Color(0xFFFFD9DC),
    secondary = Color(0xFFFFD6CC), // Warm peach
    onSecondary = Color(0xFF2A1F1A),
    secondaryContainer = Color(0xFF42342D),
    onSecondaryContainer = Color(0xFFFFE8E0),
    tertiary = Color(0xFFB3E5FC), // Light blue
    onTertiary = Color(0xFF1A2328),
    tertiaryContainer = Color(0xFF2D3A40),
    onTertiaryContainer = Color(0xFFD1F0FF),
    background = Color(0xFF1A1A1A),
    onBackground = Color(0xFFE8E8E8),
    surface = Color(0xFF1E1E1E),
    onSurface = Color(0xFFE8E8E8),
    surfaceVariant = Color(0xFF4A4458),
    onSurfaceVariant = Color(0xFFCBC4D0),
    outline = Color(0xFF958E9A),
    error = Color(0xFFFFB4AB),
    onError = Color(0xFF690005)
)

private val ExpressiveLightColorScheme = lightColorScheme(
    primary = Color(0xFFE91E63), // Vibrant pink
    onPrimary = Color.White,
    primaryContainer = Color(0xFFFFE6EA),
    onPrimaryContainer = Color(0xFF3E0015),
    secondary = Color(0xFFFF9800), // Vibrant orange
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFFFE8D4),
    onSecondaryContainer = Color(0xFF2B1700),
    tertiary = Color(0xFF2196F3), // Material blue
    onTertiary = Color.White,
    tertiaryContainer = Color(0xFFE3F2FD),
    onTertiaryContainer = Color(0xFF001D36),
    background = Color(0xFFFFFBFF),
    onBackground = Color(0xFF1C1B1F),
    surface = Color(0xFFFFFBFF),
    onSurface = Color(0xFF1C1B1F),
    surfaceVariant = Color(0xFFF3EDF7),
    onSurfaceVariant = Color(0xFF4A4458),
    outline = Color(0xFF7B7489),
    error = Color(0xFFBA1A1A),
    onError = Color.White
)

@Composable
fun AssignOneBancTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false, // Disabled for expressive theme
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> ExpressiveDarkColorScheme
        else -> ExpressiveLightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
