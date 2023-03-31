package com.gscarlos.tvshowscarlosg.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun HomeScreen(viewModel: HomeViewModel = viewModel()) {

    Text(text = "HolaMundo", color = MaterialTheme.colors.surface, modifier = Modifier.clickable {
        viewModel.loadTVShows()
    })
}