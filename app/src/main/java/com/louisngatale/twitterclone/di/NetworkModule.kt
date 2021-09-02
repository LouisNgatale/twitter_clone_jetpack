package com.louisngatale.twitterclone.di

import com.google.gson.GsonBuilder
import com.louisngatale.twitterclone.network.RetrofitService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [AppModule::class])
@InstallIn(value = [SingletonComponent::class])
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofitService() : RetrofitService {
        return Retrofit.Builder()
            .baseUrl("http://192.168.135.104/api/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(RetrofitService::class.java)
    }
}