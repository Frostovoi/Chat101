package tdLib.di

import com.example.core.api.mediator.AppProvider
import dagger.Component
import tdLib.TelegramRepository
import javax.inject.Singleton

@Singleton
@Component (
    dependencies = [AppProvider::class])
interface TelegramRepositoryComponent{

    fun repository() : TelegramRepository
}