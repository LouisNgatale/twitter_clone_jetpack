package com.louisngatale.twitterclone.network.response.authentication.login

class LoginResponseMapper {
    /*
    * Map user details from successful login
    * request into domain User model
    * */
    fun mapToUserModel(model: LoginResponse) : User{
        val user = model.user
        return User (
            id = user.id,
            username = user.username,
            email = user.email,
            name = user.name,
            profileImage = user.profileImage,
            accessToken = user.accessToken,
            phoneNumber = user.phoneNumber,
            createdAt = user.createdAt,
            dob = user.dob,
            emailVerifiedAt = user.emailVerifiedAt,
            updatedAt = user.updatedAt
        )
    }
}