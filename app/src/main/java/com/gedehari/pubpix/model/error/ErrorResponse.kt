package com.gedehari.pubpix.model.error

enum class ErrorType {
    UNKNOWN_ERROR,
    ACCESS_DENIED,
    MISSING_CREDENTIALS,
    INVALID_CREDENTIALS,
    USERNAME_EXISTS,
    INVALID_USERNAME,
    INVALID_PASSWORD,
    INVALID_LIMIT_PARAM,
    INVALID_FROM_PARAM,
    MISSING_CAPTION,
    INVALID_IMAGE
}

data class ErrorResponse(
    val err: ErrorType
)