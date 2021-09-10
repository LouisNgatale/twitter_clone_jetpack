package com.louisngatale.twitterclone.di

import android.content.Context
import com.louisngatale.twitterclone.MainApplication
import com.louisngatale.twitterclone.domain.session_manager.SessionManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesApplication(@ApplicationContext app: Context) : MainApplication {
        return app as MainApplication
    }

    @Singleton
    @Provides
    fun provideUserPreferences(@ApplicationContext context: Context) : SessionManager {
        return SessionManager(context)
    }

}