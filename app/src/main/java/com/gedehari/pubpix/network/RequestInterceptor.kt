package com.gedehari.pubpix.network

import com.gedehari.pubpix.repo.PreferenceRepository
import okhttp3.Interceptor
import okhttp3.Response

class RequestInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val oldRequest = chain.request()
        val accessToken = PreferenceRepository.accessToken!!
        if (accessToken.isBlank())
            return chain.proceed(oldRequest)
        val newRequest = oldRequest.newBuilder()
            .header("Cache-Control", "no-cache")
            .header("Authorization", "Bearer $accessToken")
            .build()
        return chain.proceed(newRequest)
    }
}