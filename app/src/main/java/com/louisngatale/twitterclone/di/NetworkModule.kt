package com.louisngatale.twitterclone.di

import com.google.gson.GsonBuilder
import com.louisngatale.twitterclone.network.LoginService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofitService() : LoginService {
        return Retrofit.Builder()
            .baseUrl("http://192.168.84.104/api/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(LoginService::class.java)
    }
}