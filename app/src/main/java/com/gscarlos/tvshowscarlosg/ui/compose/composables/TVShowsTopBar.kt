package com.gscarlos.tvshowscarlosg.ui.compose.composables

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gscarlos.tvshowscarlosg.R
import com.gscarlos.tvshowscarlosg.ui.compose.theme.TvShowsTheme
import java.util.*

@Composable
fun SearchBar(
    @SuppressLint("ModifierParameter") modifierTextField: Modifier,
    onSearch: (String) -> Unit,
    onClose: () -> Unit
) {
    var query by remember { mutableStateOf(TextFieldValue("")) }

    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(8.dp)) {
        IconButton(
            onClick = {
                onClose()
            },
        ) {
            Icon(
                modifier = Modifier.padding(0.dp),
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "Clean text button"
            )
        }
        BasicTextField(
            modifier = modifierTextField
                .weight(1f)
                .padding(8.dp),
            value = query,
            onValueChange = {
                query = it
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(onSearch = {
                if(query.text.isNotEmpty()) {
                    onSearch(query.text)
                }
            }),
            decorationBox = { innerTextField ->
                innerTextField()
                if (query.text.isEmpty()) {
                    Text(
                        text = stringResource(R.string.placeholder_search),
                        color = Color.White.copy(alpha = 0.7f)
                    )
                }

            },
            textStyle = MaterialTheme.typography.body1.copy(color = Color.White),
            singleLine = true,
            maxLines = 1,
            cursorBrush = SolidColor(Color.White)
        )
        AnimatedVisibility(visible = query.text.isNotEmpty()) {
            IconButton(
                onClick = {
                    query = TextFieldValue("")
                },
            ) {
                Icon(
                    modifier = Modifier.padding(2.dp),
                    imageVector = Icons.Filled.Close,
                    contentDescription = "Clean text button"
                )
            }
        }
    }
}

@Composable
fun TVShowsTopBar(onSearch: (String) -> Unit, onClose: () -> Unit) {
    var queryVisible by remember { mutableStateOf(false) }
    val focusRequester = FocusRequester()

    TopAppBar() {
        if (queryVisible) {
            SearchBar(
                modifierTextField = Modifier.focusRequester(focusRequester),
                onSearch = onSearch,
                onClose = {
                    onClose()
                    queryVisible = false
                }
            )
            LaunchedEffect(Unit) {
                focusRequester.requestFocus()
            }
        } else {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(start = 16.dp)
            ) {
                Text(text = Date().toLocaleString(), modifier = Modifier.weight(1f))
                IconButton(
                    onClick = {
                        queryVisible = true
                    }
                ) {
                    Icon(imageVector = Icons.Filled.Search, contentDescription = "Search bar")
                }
            }
        }
    }
}

@Preview
@Composable
fun SearchBarPreview() {
    TvShowsTheme() {
        TVShowsTopBar({}) {}
    }
}