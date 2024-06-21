package tdLib.di

import android.content.Context
import dagger.Module
import dagger.Provides
import tdLib.TelegramRepository
import javax.inject.Singleton

@Module
class TelegramRepositoryModule(private val context: Context) {

    @Provides
    @Singleton
    fun provideContext(): Context = context

    @Provides
    @Singleton
    fun provideTelegramRepository(context: Context): TelegramRepository {
        return TelegramRepository(context)
    }
}