package com.louisngatale.twitterclone.domain.model

data class User(
    val id: String,
    val username: String,
    val email: String,
    val first_name: String,
    val second_name: String,
    val profile_picture: String?,
    val token : String?
)
