package com.louisngatale.twitterclone.di

import android.content.Context
import com.google.gson.GsonBuilder
import com.louisngatale.twitterclone.BuildConfig
import com.louisngatale.twitterclone.domain.session_manager.SessionManager
import com.louisngatale.twitterclone.network.AuthApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [AppModule::class])
@InstallIn(value = [SingletonComponent::class])
object NetworkModule {

    private const val BASE_URL = "http://192.168.120.104/api/"


    @Singleton
    @Provides
    fun provideRetrofitService(
    ) : AuthApi {
//        val context = Context()
//        val session_token = SessionManager(context = )
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(
                OkHttpClient
                    .Builder()
                    .addInterceptor{ chain ->
                        chain
                            .proceed(
                            chain
                            .request()
                            .newBuilder()
                            .also {
                                it
                                    .addHeader("Accept","application/json")
                            }.build()
                        )
                    }
                    .also { client ->
                        if (BuildConfig.DEBUG){
                            val logging = HttpLoggingInterceptor()
                            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                            client.addInterceptor(logging)
                        }
                    }.build()
            )
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(AuthApi::class.java)
    }
}