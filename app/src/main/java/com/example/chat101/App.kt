package com.example.chat101

import android.app.Application
import com.example.core.api.mediator.AppWithFacade
import com.example.core.api.mediator.ProvidersFacade

class App : Application(), AppWithFacade {

    companion object {

        private var facadeComponent : FacadeComponent? = null
    }

    override fun getFacade(): ProvidersFacade {
        return facadeComponent ?: FacadeComponent.init(this).also {
            facadeComponent = it
        }
    }

    override fun onCreate() {
        super.onCreate()
        getFacade()
    }
}