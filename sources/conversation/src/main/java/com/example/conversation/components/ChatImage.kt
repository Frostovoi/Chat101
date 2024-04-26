package com.example.conversation.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ui.R
import com.example.ui.theme.ChatTheme


@Composable
fun ChatImage(
    modifier: Modifier = Modifier
) {

    var clicked by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .clip(shape = CircleShape)
            .background(color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.5f))
            .requiredSize(60.dp)
            .clickable { clicked = !clicked },
        contentAlignment = Alignment.Center
    ) {

        val angle = remember { Animatable(initialValue = 0f) }

        LaunchedEffect(clicked) {
            if (clicked) {
                angle.animateTo(
                    targetValue = -25f,
                    animationSpec = tween(
                        durationMillis = 500
                    ),
                )
                angle.animateTo(
                    targetValue = 0f,
                    animationSpec = tween(
                        durationMillis = 500
                    )
                )
                clicked = !clicked
            }
        }

        Image(
            painter = painterResource(id = R.drawable.icon_101_left),
            contentDescription = null,
            modifier = modifier.requiredSize(50.dp)
        )

        Image(
            painter = painterResource(id = R.drawable.icon_101_mid),
            contentDescription = null,
            modifier = modifier.requiredSize(50.dp)
        )

        Image(
            painter = painterResource(id = R.drawable.icon_101_right_2),
            contentDescription = null,
            modifier = modifier
                .graphicsLayer(
                    transformOrigin = TransformOrigin(
                        pivotFractionX = 0.9f,
                        pivotFractionY = 0.85f,
                    ),
                    rotationZ =  angle.value
                )
                .requiredSize(50.dp)
        )
    }
}


@Preview
@Composable
fun ChatImagePreview() {
    ChatTheme {
        ChatImage(modifier = Modifier
            .padding(16.dp))
    }
}