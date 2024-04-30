package com.example.login.di

import com.example.core.api.mediator.ProvidersFacade
import com.example.login.LoginFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component (
    dependencies = [ProvidersFacade::class]
)
interface LoginComponent {

    companion object {

        fun create(providersFacade: ProvidersFacade): LoginComponent {
            return DaggerLoginComponent.builder().providersFacade(providersFacade).build()
        }
    }

    fun inject(loginFragment: LoginFragment)
}