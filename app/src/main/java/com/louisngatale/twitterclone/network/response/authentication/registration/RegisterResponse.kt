package com.louisngatale.twitterclone.network.response.authentication.registration

import com.google.gson.annotations.SerializedName

data class RegisterResponse(
    @SerializedName("message")
    val message: String?,
    @SerializedName("errors")
    val errors: HashMap<String, ArrayList<Any>>? = hashMapOf(),
)
