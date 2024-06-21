package com.example.core.factory

import com.example.core.api.database.DatabaseProvider
import com.example.core.api.mediator.AppProvider
import com.example.core.impl.DaggerDatabaseComponent

object CoreProvidersFactory {

    fun createDatabaseBuilder(appProvider: AppProvider): DatabaseProvider {
        return DaggerDatabaseComponent
            .builder()
            .appProvider(appProvider)
            .build()
    }



}