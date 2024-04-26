package com.example.conversation


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ui.theme.ChatTheme


@Composable
fun UserInput(
    onMessageSent: (String) -> Unit,
    modifier: Modifier = Modifier,
    resetScroll: () -> Unit = {}
) {

    var textState by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue())
    }

    var textFieldFocusState by remember { mutableStateOf(false) }

    Surface(
        shadowElevation = 10.dp,
        tonalElevation = 4.dp,
        contentColor = MaterialTheme.colorScheme.secondary,
    ) {
        Row(modifier = modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.primaryContainer)
        ) {
            UserInputTextField(
                textFieldValue = textState,
                onTextChanged = { textFieldValue -> textState = textFieldValue},
                onTextFieldFocused = { focused ->
                    if(focused) {
                        resetScroll()
                    }
                    textFieldFocusState = focused
                },
                keyboardType = KeyboardType.Text,
                focusState = textFieldFocusState,
            )

            Spacer(modifier.width(20.dp))


            SendButton(
                onMessageSent =  {
                    onMessageSent(textState.text)
                    textState = TextFieldValue()
                    resetScroll()
                },
                sendMessageEnabled = textState.text.isNotBlank()
            )
        }
    }
}


@Composable
fun SendButton(
    sendMessageEnabled: Boolean,
    onMessageSent: () -> Unit,
) {
    val border = if (!sendMessageEnabled) {
        BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
        )
    } else {
        null
    }

    val disabledContentColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f)

    val buttonColors = ButtonDefaults.buttonColors(
        disabledContainerColor = Color.Transparent,
        disabledContentColor = disabledContentColor,
        containerColor = MaterialTheme.colorScheme.primary
    )

    ElevatedButton(
        modifier = Modifier
            .wrapContentSize(),
        onClick = onMessageSent,
        colors = buttonColors,
        enabled = sendMessageEnabled,
        border = border
    ) {
        Text(
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            text = stringResource(id = com.example.ui.R.string.send_button_text)
        )
    }
}

@Composable
private fun UserInputTextField(
    textFieldValue: TextFieldValue,
    onTextChanged: (TextFieldValue) -> Unit,
    onTextFieldFocused: (Boolean) -> Unit,
    keyboardType: KeyboardType,
    focusState: Boolean,
    modifier: Modifier = Modifier
) {

    var lastFocusState by remember { mutableStateOf(false) }

    BasicTextField(
        value = textFieldValue,
        onValueChange = { onTextChanged(it)},
        modifier = modifier
            .padding(start = 16.dp, top = 8.dp)
            .onFocusChanged { state ->
                if (lastFocusState != state.isFocused) {
                    onTextFieldFocused(state.isFocused)
                }
                lastFocusState = state.isFocused
            }
            .clip(RoundedCornerShape(20.dp))
            .background(color = MaterialTheme.colorScheme.surface)
            .fillMaxWidth(0.7f)
            .defaultMinSize(minHeight = 30.dp),
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = ImeAction.Send
        ),
        maxLines = 1,
        cursorBrush = SolidColor(LocalContentColor.current),
        textStyle = LocalTextStyle.current.copy(color = LocalContentColor.current),
        decorationBox = {
            if (textFieldValue.text.isEmpty() && !focusState)  {
                val disableContentColor = MaterialTheme.colorScheme.onSurfaceVariant
                Text(
                    text = stringResource(id = com.example.ui.R.string.text_field_hint),
                    color = disableContentColor,
                    style = MaterialTheme.typography.bodyLarge.copy(color = disableContentColor),
                    modifier = modifier.padding(start = 8.dp, top = 4.dp)
                )
            }
        }
    )
}

@Preview
@Composable
fun UserInputPreview() {
    ChatTheme {
        UserInput(onMessageSent = {})
    }
}

@Preview
@Composable
fun SendButtonPreview() {
    ChatTheme {
        SendButton(sendMessageEnabled = true) {
            
        }
    }
}

