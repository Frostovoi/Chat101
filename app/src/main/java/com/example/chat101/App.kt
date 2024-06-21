package com.example.chat101

import android.app.Application
import com.example.core.api.mediator.AppWithFacade
import com.example.core.api.mediator.ProvidersFacade
import tdLib.di.DaggerTelegramRepositoryComponent
import tdLib.di.TelegramRepositoryComponent

class App : Application(), AppWithFacade {

    companion object {

        private var facadeComponent : FacadeComponent? = null


        private var tgRepositoryComponent: TelegramRepositoryComponent? = null
    }

    override fun getFacade(): ProvidersFacade {
        return facadeComponent ?: FacadeComponent.init(this).also {
            facadeComponent = it
        }
    }

    private fun getTgRepository(): TelegramRepositoryComponent {
        return tgRepositoryComponent ?: DaggerTelegramRepositoryComponent.factory().create(applicationContext)
    }

    override fun onCreate() {
        super.onCreate()
        getFacade()
        getTgRepository()
    }
}