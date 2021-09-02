package com.louisngatale.twitterclone.network.model.authentication.registration

import com.google.gson.annotations.SerializedName

data class RegisterDTO(
    @SerializedName("message")
    val message: String?,
    @SerializedName("errors")
    val errors: HashMap<String, ArrayList<Any>>? = hashMapOf(),
)
