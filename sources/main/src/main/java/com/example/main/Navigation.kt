package com.example.main

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.login.LoginContent
import com.example.login.di.DaggerLoginComponent
import com.example.register.Register
import com.example.ui.theme.ChatTheme


@Composable
fun Navigation(){
    val navController = rememberNavController()
    ChatTheme {
        NavHost(
            navController = navController,
            startDestination = Screens.LOGIN
        ){
            composable(Screens.LOGIN) {
                val viewModel = DaggerLoginComponent.builder().build().getViewModel()
                LoginContent(
                    viewModel = viewModel,
                    onSignInPressed = {},
                    onSignUpPressed = { navController.navigate(Screens.SIGN_UP)},
                )
            }
            composable(Screens.SIGN_UP) {
                Register()
            }
        }
    }
}
