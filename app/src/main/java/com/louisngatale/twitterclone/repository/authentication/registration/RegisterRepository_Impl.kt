package com.louisngatale.twitterclone.repository.authentication.registration

import com.louisngatale.twitterclone.network.RegisterService
import com.louisngatale.twitterclone.network.RetrofitService
import com.louisngatale.twitterclone.network.model.authentication.registration.RegisterDTO
import com.louisngatale.twitterclone.network.request.RegisterRequest

class RegisterRepository_Impl (
    private val registerService: RetrofitService,
): RegisterRepository {
    override suspend fun register(register_request: RegisterRequest): RegisterDTO {
        return registerService.register(register_request)
    }
}