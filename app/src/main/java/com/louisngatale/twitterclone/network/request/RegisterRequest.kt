package com.louisngatale.twitterclone.network.request

import com.google.gson.annotations.SerializedName

data class RegisterRequest (
    @SerializedName("email")
    val email : String,
    @SerializedName("username")
    val username : String,
    @SerializedName("phone_number")
    val phone_number : String,
    @SerializedName("password")
    val password : String,
    @SerializedName("name")
    val name : String,
    @SerializedName("dob")
    val dob : String,
)