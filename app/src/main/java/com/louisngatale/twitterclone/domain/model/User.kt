package com.louisngatale.twitterclone.domain.model

data class User(
    val access_token: String,
    val created_at: String,
    val dob: String,
    val email: String,
    val email_verified_at: Any?,
    val id: Int,
    val name: String,
    val phone_number: Int,
    val profile_image: Any?,
    val updated_at: Any?,
    val username: String
)