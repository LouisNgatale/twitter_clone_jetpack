package com.louisngatale.twitterclone.repository.authentication.login

import com.louisngatale.twitterclone.network.RetrofitService
import com.louisngatale.twitterclone.network.model.authentication.login.LoginDTO
import com.louisngatale.twitterclone.network.model.authentication.login.LoginDtoMapper
import com.louisngatale.twitterclone.network.request.LoginRequest

class LoginRepository_Impl(
    private val loginService: RetrofitService,
) : LoginRepository{

    /*
    * This function makes login request to the server and the converts
    * the data to the domain model
    * */
    override suspend fun login(login_request: LoginRequest): LoginDTO {
        return loginService.login(login_request)
    }
}