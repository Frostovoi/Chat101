package com.example.core.api.mediator

import android.content.Context


interface AppProvider {

    fun provideContext() : Context
}