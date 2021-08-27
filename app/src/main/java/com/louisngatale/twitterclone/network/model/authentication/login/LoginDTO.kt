package com.louisngatale.twitterclone.network.model.authentication.login

import com.google.gson.annotations.SerializedName

data class LoginDTO(
    @SerializedName("user_id")
    val user_id : String,
    @SerializedName("username")
    val username: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("firstname")
    val first_name: String,
    @SerializedName("secondname")
    val second_name: String,
    @SerializedName("profile_picture")
    val profile_picture: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("response_message")
    val response_message: String,
    @SerializedName("token")
    val token: String,
)
