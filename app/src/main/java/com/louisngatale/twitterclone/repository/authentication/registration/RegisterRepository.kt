package com.louisngatale.twitterclone.repository.authentication.registration

import com.louisngatale.twitterclone.network.model.authentication.registration.RegisterDTO
import com.louisngatale.twitterclone.network.request.RegisterRequest

interface RegisterRepository {
    suspend fun register(register_request: RegisterRequest) : RegisterDTO
}