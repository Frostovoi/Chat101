package com.example.chat101

import android.app.Application
import com.example.core.api.database.DatabaseProvider
import dagger.Component
import com.example.core.api.mediator.AppProvider
import com.example.core.api.mediator.ProvidersFacade
import com.example.core.factory.CoreProvidersFactory


@Component(
    dependencies = [AppProvider::class, DatabaseProvider::class],
    modules = [MediatorsBindings::class]
)
interface FacadeComponent : ProvidersFacade {

    companion object {

        fun init(application: Application): FacadeComponent {
            val appComponent = AppComponent.create(application)
            return DaggerFacadeComponent
                .builder()
                .appProvider(appComponent)
                .databaseProvider(CoreProvidersFactory.createDatabaseBuilder(appComponent))
                .build()
        }
    }
}