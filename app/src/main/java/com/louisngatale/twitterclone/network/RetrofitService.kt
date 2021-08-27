package com.louisngatale.twitterclone.network

import com.louisngatale.twitterclone.network.model.authentication.login.LoginDTO
import com.louisngatale.twitterclone.network.request.LoginRequest
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface LoginService {
    @POST("login")
    suspend fun login(@Body login_request : LoginRequest) : LoginDTO
}