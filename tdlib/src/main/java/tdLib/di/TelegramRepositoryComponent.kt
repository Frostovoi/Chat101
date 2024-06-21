package tdLib.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import tdLib.TelegramRepository

@Component
interface TelegramRepositoryComponent {


    fun telegramRepository(): TelegramRepository


    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): TelegramRepositoryComponent
    }



//
//    companion object {
//        fun init(context: Context): TelegramRepositoryComponent {
//            return DaggerTelegramRepositoryComponent.factory().create(context)
//        }
//    }
}