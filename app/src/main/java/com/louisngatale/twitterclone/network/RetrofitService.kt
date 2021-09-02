package com.louisngatale.twitterclone.network

import com.louisngatale.twitterclone.network.model.authentication.login.LoginDTO
import com.louisngatale.twitterclone.network.model.authentication.registration.RegisterDTO
import com.louisngatale.twitterclone.network.request.LoginRequest
import com.louisngatale.twitterclone.network.request.RegisterRequest
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface RegisterService {
    @POST("register")
    suspend fun register(@Body register_request : RegisterRequest) : RegisterDTO
}

interface RetrofitService {
    @POST("login")
    suspend fun login(@Body login_request : LoginRequest) : LoginDTO

    @POST("register")
    suspend fun register(@Body register_request : RegisterRequest) : RegisterDTO
}