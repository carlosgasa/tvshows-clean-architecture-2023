package com.gscarlos.tvshowscarlosg.ui.compose.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gscarlos.tvshowscarlosg.ui.compose.theme.BorderCardLight
import com.gscarlos.tvshowscarlosg.ui.compose.theme.Shapes

@Composable
fun BaseCard(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    Card(
        elevation = 0.dp,
        border = BorderStroke(2.dp, BorderCardLight), shape = Shapes.large,
        modifier = modifier,
        content = content
    )
}