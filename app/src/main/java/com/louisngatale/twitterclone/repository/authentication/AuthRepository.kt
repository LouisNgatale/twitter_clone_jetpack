package com.louisngatale.twitterclone.repository.authentication

import com.louisngatale.twitterclone.domain.session_manager.SessionManager
import com.louisngatale.twitterclone.network.AuthApi
import com.louisngatale.twitterclone.network.request.LoginRequest
import com.louisngatale.twitterclone.repository.BaseRepository


class AuthRepository(
    private val authApi: AuthApi,
    private val preferences: SessionManager
) : BaseRepository {

    suspend fun login(
        login : LoginRequest
    ) = safeApiCall {
        authApi.login(login)
    }

    suspend fun logout(
        token: String
    ) = safeApiCall {
        authApi.logout(token)
    }

    suspend fun saveAuthToken(token: String){
        preferences.saveAuthToken(token)
    }
}