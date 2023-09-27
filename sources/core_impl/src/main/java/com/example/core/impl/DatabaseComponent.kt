package com.example.core.impl

import com.example.core.api.database.DatabaseProvider
import com.example.core.api.mediator.AppProvider
import dagger.Component


@Component(
    dependencies = [AppProvider::class],
    modules = [DatabaseModule::class]
)
interface DatabaseComponent : DatabaseProvider {
}