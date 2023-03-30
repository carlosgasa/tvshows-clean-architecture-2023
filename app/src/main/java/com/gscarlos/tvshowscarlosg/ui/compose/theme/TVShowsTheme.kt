package com.gscarlos.tvshowscarlosg.ui.compose.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import com.gscarlos.moviescleanarchitecture.ui.compose.theme.DarkColorPalette
import com.gscarlos.moviescleanarchitecture.ui.compose.theme.LightColorPalette

@Composable
fun TvShowsTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
    MaterialTheme(
        typography = Typography,
        colors = if(darkTheme) DarkColorPalette else LightColorPalette,
        shapes = Shapes,
        content = content
    )
}