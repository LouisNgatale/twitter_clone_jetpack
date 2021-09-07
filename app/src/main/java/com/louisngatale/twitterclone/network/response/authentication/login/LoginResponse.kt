package com.louisngatale.twitterclone.network.response.authentication.login


import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("user")
    val user: User
)