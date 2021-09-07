package com.louisngatale.twitterclone.repository.authentication.login

import com.louisngatale.twitterclone.network.request.LoginRequest
import com.louisngatale.twitterclone.network.response.authentication.login.LoginResponse

interface LoginRepository {
    suspend fun login(login_request : LoginRequest) : LoginResponse
}