package com.gedehari.pubpix.model.login

data class LoginResponseJson(
    val accessToken: String,
    val refreshToken: String,
    val expiresIn: String
)