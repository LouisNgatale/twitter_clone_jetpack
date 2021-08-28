package com.louisngatale.twitterclone.network.model.authentication.login

import com.louisngatale.twitterclone.domain.model.User
import com.louisngatale.twitterclone.network.request.LoginRequest

class LoginDtoMapper {
    /*
    * Map user details from successful login
    * request into domain User model
    * */
    fun mapToUserModel(model: LoginDTO) : User{
        return User (
            user_id = model.user_id,
            username = model.username,
            email = model.email,
            first_name = model.first_name,
            second_name = model.second_name,
            profile_picture = model.profile_picture,
            token = model.token
        )
    }
}