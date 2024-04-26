package com.example.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.ChatTheme
import com.example.ui.theme.Red80
import com.example.ui.theme.Violet


@Composable
fun LoginContent(
    modifier: Modifier = Modifier,
    onSignInPressed: () -> Unit = {},
    onSignUpPressed: () -> Unit = {}
) {
    Box(modifier = modifier.fillMaxSize()){

        Image(
            painter = painterResource(id = com.example.ui.R.drawable.back_orange_warm),
            contentDescription = "",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.matchParentSize()
        )
        
        Column(
            modifier = Modifier
                .matchParentSize()
                .padding(bottom = 100.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            val textFieldShape = RoundedCornerShape(30.dp)
            var loginTextField by remember { mutableStateOf("") }
            var passwordTextField by remember { mutableStateOf("") }

            OutlinedTextField(

                value = loginTextField,
                onValueChange = { loginTextField = it},
                modifier = Modifier
                    .padding(bottom = 12.dp)
                    .background(
                        color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f),
                        shape = textFieldShape
                    ),
                shape = textFieldShape,
                placeholder = {
                    Text("Enter your Login")
                }
            )

            OutlinedTextField(
                value = passwordTextField,
                onValueChange = { passwordTextField = it},
                placeholder = {
                    Text("Enter your Password")
                },
                modifier = Modifier.background(
                    color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f),
                    shape = textFieldShape
                ),
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password
                ),
                shape = textFieldShape
            )

            Spacer(modifier = Modifier.padding(24.dp))

            Row {

                val buttonColors = ButtonDefaults.buttonColors(
                    containerColor = Red80,
                    contentColor = Violet//BlueGrey30
                )

                val buttonElevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 30.dp,
                    pressedElevation = 10.dp
                )

                ElevatedButton(
                    onClick = onSignInPressed,
                    colors = buttonColors,
                    elevation = buttonElevation
                ) {
                    Text(
                        text = stringResource(id = com.example.ui.R.string.sign_in_button),
                        fontSize = 16.sp,
                    )
                }

                Spacer(modifier = Modifier.padding(16.dp))

                ElevatedButton(
                    onClick = onSignUpPressed,
                    colors = buttonColors,
                    elevation = buttonElevation
                )
                {
                    Text(
                        text = stringResource(id = com.example.ui.R.string.sign_up_button),
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}


@Preview
@Composable
fun LoginContentPreview() {
    ChatTheme {
        LoginContent()
    }
}

