package com.louisngatale.twitterclone.di

import com.louisngatale.twitterclone.network.LoginService
import com.louisngatale.twitterclone.network.model.authentication.login.LoginDtoMapper
import com.louisngatale.twitterclone.repository.authentication.login.LoginRepository
import com.louisngatale.twitterclone.repository.authentication.login.LoginRepository_Impl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    /*
    * Inject required dependencies into the login
    * repository which makes login request on
    * behalf of the view model
    * */
    @Singleton
    @Provides
    fun provideLoginRepository(
        loginService: LoginService,
    ):LoginRepository{
        return LoginRepository_Impl(
            loginService = loginService,
        )
    }
}