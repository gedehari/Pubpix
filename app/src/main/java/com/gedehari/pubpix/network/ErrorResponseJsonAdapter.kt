package com.gedehari.pubpix.network

import com.gedehari.pubpix.model.error.ErrorResponse
import com.gedehari.pubpix.model.error.ErrorResponseJson
import com.gedehari.pubpix.model.error.ErrorType
import com.squareup.moshi.FromJson

class ErrorResponseJsonAdapter {
    @FromJson
    fun fromJson(json: ErrorResponseJson): ErrorResponse {
        return ErrorResponse(
            ErrorType.valueOf(json.err)
        )
    }
}