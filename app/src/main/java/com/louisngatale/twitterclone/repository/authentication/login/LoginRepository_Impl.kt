package com.louisngatale.twitterclone.repository.authentication.login

import com.louisngatale.twitterclone.network.AuthApi
import com.louisngatale.twitterclone.network.request.LoginRequest
import com.louisngatale.twitterclone.network.response.authentication.login.LoginResponse

class LoginRepository_Impl(
    private val loginService: AuthApi,
) : LoginRepository{

    /*
    * This function makes login request to the server and the converts
    * the data to the domain model
    * */
    override suspend fun login(login_request: LoginRequest): LoginResponse {
        return loginService.login(login_request)
    }
}