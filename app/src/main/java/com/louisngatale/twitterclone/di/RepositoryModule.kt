package com.louisngatale.twitterclone.di

import com.louisngatale.twitterclone.network.RegisterService
import com.louisngatale.twitterclone.network.RetrofitService
import com.louisngatale.twitterclone.network.model.authentication.login.LoginDtoMapper
import com.louisngatale.twitterclone.repository.authentication.login.LoginRepository
import com.louisngatale.twitterclone.repository.authentication.login.LoginRepository_Impl
import com.louisngatale.twitterclone.repository.authentication.registration.RegisterRepository
import com.louisngatale.twitterclone.repository.authentication.registration.RegisterRepository_Impl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module(includes = [AppModule::class])
@InstallIn(value = [SingletonComponent::class])
object RepositoryModule {

    /*
    * Inject required dependencies into the login
    * repository which makes login request on
    * behalf of the view model
    * */
    @Singleton
    @Provides
    fun provideLoginRepository(
        loginService: RetrofitService,
    ):LoginRepository{
        return LoginRepository_Impl(
            loginService = loginService,
        )
    }

    /*
    * Inject required dependencies into the register
    * repository which makes register request on
    * behalf of the view model
    * */
    @Singleton
    @Provides
    fun provideRegisterRepository(
        registerService: RetrofitService,
    ):RegisterRepository{
        return RegisterRepository_Impl(
            registerService = registerService,
        )
    }
}