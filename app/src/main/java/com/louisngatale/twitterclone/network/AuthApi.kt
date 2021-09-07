package com.louisngatale.twitterclone.network

import com.louisngatale.twitterclone.network.response.authentication.registration.RegisterResponse
import com.louisngatale.twitterclone.network.request.LoginRequest
import com.louisngatale.twitterclone.network.request.RegisterRequest
import com.louisngatale.twitterclone.network.response.authentication.login.LoginResponse
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST


interface AuthApi {

    @POST("auth/login")
    suspend fun login(
        @Body login_request : LoginRequest
    ) : LoginResponse

    @POST("logout")
    suspend fun logout(
        @Header("Authorization") token: String
    ) : LoginResponse

    @POST("auth/register")
    suspend fun register(@Body register_request : RegisterRequest) : RegisterResponse
}