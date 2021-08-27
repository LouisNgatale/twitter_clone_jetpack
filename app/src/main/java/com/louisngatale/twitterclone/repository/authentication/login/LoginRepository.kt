package com.louisngatale.twitterclone.repository.authentication.login

import com.louisngatale.twitterclone.domain.model.User
import com.louisngatale.twitterclone.network.model.authentication.login.LoginDTO
import com.louisngatale.twitterclone.network.request.LoginRequest

interface LoginRepository {
    suspend fun login(login_request : LoginRequest) : LoginDTO
}