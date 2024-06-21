package com.example.chat101

import android.app.Application
import android.content.Context
import com.example.core.api.mediator.AppProvider
import dagger.BindsInstance
import dagger.Component
import tdLib.TelegramRepository
import tdLib.di.TelegramRepositoryComponent
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AppModule::class]
)
interface AppComponent : AppProvider {

    companion object {

        private var appComponent: AppProvider? = null

        fun create(application: Application) : AppProvider {
            return appComponent ?: DaggerAppComponent
                .builder()
                .application(application.applicationContext)
                .build().also {
                    appComponent = it
                }
        }
    }


    fun telegramRepository(): TelegramRepository


    @Component.Builder
    interface Builder {


        @BindsInstance
        fun application(context: Context): Builder


        fun build() : AppComponent
    }
}