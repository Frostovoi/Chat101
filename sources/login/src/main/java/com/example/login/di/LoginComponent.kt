package com.example.login.di

import com.example.login.LoginViewModel
import dagger.Component
import javax.inject.Scope


@Scope
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class LoginScope

@Component(
    modules = [LoginModule::class]
)
@LoginScope
interface LoginComponent {

    @Component.Builder
    interface Builder {
        fun build(): LoginComponent
    }

    fun getViewModel(): LoginViewModel
}