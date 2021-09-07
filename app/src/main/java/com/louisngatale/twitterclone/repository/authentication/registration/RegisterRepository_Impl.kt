package com.louisngatale.twitterclone.repository.authentication.registration

import com.louisngatale.twitterclone.network.AuthApi
import com.louisngatale.twitterclone.network.response.authentication.registration.RegisterResponse
import com.louisngatale.twitterclone.network.request.RegisterRequest

class RegisterRepository_Impl (
    private val registerService: AuthApi,
): RegisterRepository {
    override suspend fun register(register_request: RegisterRequest): RegisterResponse {
        return registerService.register(register_request)
    }
}