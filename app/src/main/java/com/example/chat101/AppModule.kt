package com.example.chat101

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class AppModule {

    @Module
    companion object {

        private const val PREFS_NAME = "CHAT_SP"

        @JvmStatic
        @Provides
        @Singleton
        fun provideSharedPreferences(context: Context) : SharedPreferences {
            return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        }

    }
}