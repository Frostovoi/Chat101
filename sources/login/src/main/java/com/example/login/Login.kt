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
import androidx.compose.runtime.collectAsState
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
import tdLib.AuthState


@Composable
fun LoginContent(
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel,
    onSignInPressed: () -> Unit = {},
    onSignUpPressed: () -> Unit = {},
) {


    val authState = viewModel.authState.collectAsState(initial = AuthState.Initial)
    viewModel.performAuthResult()


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
            var phoneTextField by remember { mutableStateOf("") }
            var codeTextField by remember { mutableStateOf("") }

            OutlinedTextField(

                value = phoneTextField,
                onValueChange = { phoneTextField = it},
                modifier = Modifier
                    .padding(bottom = 12.dp)
                    .background(
                        color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f),
                        shape = textFieldShape
                    ),
                shape = textFieldShape,
                placeholder = {
                    Text("Enter your telephone number")
                }
            )

            OutlinedTextField(
                value = codeTextField,
                onValueChange = { codeTextField = it},
                placeholder = {
                    Text("Enter the code")
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
                    onClick = {
                        viewModel.sendPhone(phoneTextField)
                              },
                    colors = buttonColors,
                    elevation = buttonElevation
                ) {
                    Text(
                        text = stringResource(id = com.example.ui.R.string.send_code),
                        fontSize = 16.sp,
                    )
                }

                Spacer(modifier = Modifier.padding(16.dp))

                ElevatedButton(
                    onClick = {
                        viewModel.sendCode(codeTextField)
                    },
                    colors = buttonColors,
                    elevation = buttonElevation
                )
                {
                    Text(
                        text = stringResource(id = com.example.ui.R.string.sign_in_button),
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
        //LoginContent()
    }
}

