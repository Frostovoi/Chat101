package com.example.ui.theme

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val ChatLightColorScheme = lightColorScheme(
    primary = LightOrange60,
    onPrimary = Color.White,
    primaryContainer = LightOrange90,
    onPrimaryContainer = LightOrange10,
    inversePrimary = LightOrange80,
    secondary = Green40,
    onSecondary = Color.White,
    secondaryContainer = Green90,
    onSecondaryContainer = Green20,
    tertiary = Yellow40,
    onTertiary = Color.White,
    tertiaryContainer = Yellow90,
    onTertiaryContainer = Yellow10,
    error = Red40,
    onError = Color.White,
    errorContainer = Red90,
    onErrorContainer = Red10,
    background = Grey99,
    onBackground = Grey10,
    surface = Grey99,
    onSurface = Grey10,
    inverseSurface = Grey20,
    inverseOnSurface = Grey95,
    surfaceVariant = BlueGrey90,
    onSurfaceVariant = BlueGrey30,
    outline = BlueGrey50
)

private val ChatDarkColorScheme = darkColorScheme(
    primary = DarkGrey,
    onPrimary = LightOrange20,
    primaryContainer = DarkBlue15,
    onPrimaryContainer = LightBlue90,
    inversePrimary = DarkBlue05,
    secondary = DarkGreen80,
    onSecondary = DarkGreen20,
    secondaryContainer = DarkBlue05,
    onSecondaryContainer = LightBlue70,
    tertiary = Yellow80,
    onTertiary = Yellow20,
    tertiaryContainer = Yellow30,
    onTertiaryContainer = Yellow90,
    error = Red80,
    onError = Red20,
    errorContainer = Red30,
    onErrorContainer = Red90,
    background = Grey10,
    onBackground = Grey90,
    surface = Grey10,
    onSurface = Grey80,
    inverseSurface = Grey90,
    inverseOnSurface = Grey20,
    surfaceVariant = BlueGrey30,
    onSurfaceVariant = BlueGrey80,
    outline = BlueGrey60
)

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("NewApi")
@Composable
fun ChatTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    isDynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {

    val myColorScheme = when {

        isDarkTheme -> ChatDarkColorScheme.also {
            TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = ChatDarkColorScheme.onPrimaryContainer
            )
        }
        else -> ChatLightColorScheme.also {
            TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = ChatDarkColorScheme.onPrimaryContainer
            )
        }
    }

    MaterialTheme(
        colorScheme = myColorScheme,
        typography = ChatTypography,
        content = content
    )
}