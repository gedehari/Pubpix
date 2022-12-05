package com.gedehari.pubpix.model

data class LoginRequestJson(
    val username: String,
    val password: String
)

data class LoginResponseJson(
    val accessToken: String,
    val refreshToken: String,
    val expiresIn: String
)