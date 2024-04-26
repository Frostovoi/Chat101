package com.example.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import com.example.ui.R
import com.example.ui.theme.BlueGrey30
import com.example.ui.theme.ChatTheme
import com.example.ui.theme.Red80

@Composable
fun Register(
    modifier: Modifier = Modifier,
    onSignUpConfirmed: () -> Unit = {}
){
    Box(modifier = modifier.fillMaxSize()){

        Image(
            painter = painterResource(id = R.drawable.back_orange_warm),
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
            var passwordTextFieldAgain by remember { mutableStateOf("") }
            var emailTextField by remember { mutableStateOf("")}

            val itemModifier = Modifier
                .padding(bottom = 12.dp)
                .background(
                    color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f),
                    shape = textFieldShape
                )

            OutlinedTextField(
                value = emailTextField,
                onValueChange = { emailTextField = it},
                placeholder = {
                    Text("Enter your E-mail")
                },
                modifier = itemModifier,
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password
                ),
                shape = textFieldShape
            )

            OutlinedTextField(

                value = loginTextField,
                onValueChange = { loginTextField = it},
                modifier = itemModifier,
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
                modifier = itemModifier,
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password
                ),
                shape = textFieldShape
            )

            OutlinedTextField(
                value = passwordTextFieldAgain,
                onValueChange = { passwordTextFieldAgain = it},
                placeholder = {
                    Text("Enter your Password Again")
                },
                modifier = itemModifier,
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password
                ),
                shape = textFieldShape
            )

                val buttonColors = ButtonDefaults.buttonColors(
                    containerColor = Red80,
                    contentColor = BlueGrey30
                )


                Spacer(modifier = Modifier.padding(16.dp))

                Button(
                    onClick = onSignUpConfirmed,
                    colors = buttonColors
                )
                {
                    Text(text = stringResource(id = R.string.sign_up_button))
                }
        }
    }
}

@Preview
@Composable
fun RegisterPreview() {
    ChatTheme {
        Register()
    }
}
