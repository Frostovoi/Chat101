package com.example.chat101

import com.example.core.api.mediator.LoginMediator
import com.example.login.LoginMediatorImpl
import dagger.Binds
import dagger.Module
import dagger.Reusable


@Module
interface MediatorsBindings {

    @Binds
    @Reusable
    fun bindsLoginMediator(loginMediatorImpl: LoginMediatorImpl): LoginMediator
}