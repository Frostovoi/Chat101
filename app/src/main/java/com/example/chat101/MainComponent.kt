package com.example.chat101

import com.example.core.api.mediator.ProvidersFacade
import dagger.Component

@Component(
    dependencies = [ProvidersFacade::class]
)
interface MainComponent {

    companion object {

        fun create(providersFacade: ProvidersFacade): MainComponent {
            return DaggerMainComponent.builder().providersFacade(providersFacade).build()
        }
    }


    fun inject(mainActivity: MainActivity)
}