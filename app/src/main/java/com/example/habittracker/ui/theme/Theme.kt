package com.example.habittracker.ui.theme


import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColors = lightColorScheme(
    primary = Color(0xFFFFFFFF),
    onPrimary = Color.White,
    secondary = Color(0xFFe6e6e6 ),
    onSecondary = Color.Black,
    background = Color(0xFFFFFFFF),
    surface = Color(0xFFFFFFFF),

)

private val DarkColors = darkColorScheme(
    primary = Color(0xFFFFFFFF),
    onPrimary = Color.White,
    secondary = Color(0xFF191919),
    onSecondary = Color(0xFF191919),
    background = Color(0xFF000000),
    surface = Color(0xFF000000),
)


@Composable
fun HabitTheme(
    darkTheme: Boolean = isSystemInDarkTheme(), // Use system dark mode setting by default
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColors else LightColors

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        content = content
    )
}