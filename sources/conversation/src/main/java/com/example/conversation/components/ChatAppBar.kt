package com.example.conversation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ui.theme.ChatTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatAppBar (
    modifier: Modifier = Modifier,
    scrollBehaviour: TopAppBarScrollBehavior? = null,
    onNavIconPressed: () -> Unit = { },
    title: @Composable () -> Unit,
    actions: @Composable RowScope.() -> Unit = {}
) {
    CenterAlignedTopAppBar(
        modifier = modifier
            .shadow(
                elevation = 10.dp,
                shape = RoundedCornerShape(bottomEnd = 15.dp, bottomStart = 15.dp)
            )
            .clip(shape = RoundedCornerShape(bottomEnd = 15.dp, bottomStart = 15.dp)),
        actions = actions,
        title = title,
        scrollBehavior = scrollBehaviour,
        navigationIcon = {
            ChatImage(
                modifier = modifier
                    .padding(8.dp)
                    .clickable(onClick = onNavIconPressed)
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun ChatAppBarPreview() {
    ChatTheme {
        ChatAppBar(title = { Text("Preview!") })
    }
}


