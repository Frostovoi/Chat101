package com.example.login.di

import com.example.login.LoginViewModel
import dagger.Module
import dagger.Provides


@Module
class LoginModule {

    @Provides
    @LoginScope
    fun provideLoginViewModel() : LoginViewModel = LoginViewModel()
}