package com.gedehari.pubpix.model.error

import com.haroldadmin.cnradapter.NetworkResponse

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
) {
    companion object {
        fun <T: Any> getErrorResponseDesc(error: NetworkResponse.Error<T, ErrorResponse>): String? {
            when (error) {
                is NetworkResponse.ServerError -> {
                    return "Server error! Please contact the author if the problem persists."
                }
                is NetworkResponse.NetworkError -> {
                    return "Network error! Please check your internet connection."
                }
                else -> {}
            }
            return null
        }
    }
};