package com.gedehari.pubpix.repo

import com.gedehari.pubpix.model.error.ErrorResponse
import com.gedehari.pubpix.model.login.LoginRequestJson
import com.gedehari.pubpix.model.login.LoginResponseJson
import com.gedehari.pubpix.model.login.RefreshRequestJson
import com.gedehari.pubpix.network.ApiService
import com.haroldadmin.cnradapter.NetworkResponse

object AuthRepository {
    suspend fun login(username: String, password: String): NetworkResponse<LoginResponseJson, ErrorResponse> {
        val request = LoginRequestJson(username, password)
        val response = ApiService.getClient().signIn(request)
        when (response) {
            is NetworkResponse.Success -> {
                PreferenceRepository.accessToken = response.body.accessToken
                PreferenceRepository.refreshToken = response.body.refreshToken
            }
            else -> {}
        }

        return response
    }

    suspend fun refresh(): NetworkResponse<LoginResponseJson, ErrorResponse> {
        val request = RefreshRequestJson(PreferenceRepository.refreshToken!!)
        val response = ApiService.getClient().refresh(request)
        when (response) {
            is NetworkResponse.Success -> {
                PreferenceRepository.accessToken = response.body.accessToken
                PreferenceRepository.refreshToken = response.body.refreshToken
            }
            is NetworkResponse.ServerError -> {
                PreferenceRepository.accessToken = ""
                PreferenceRepository.refreshToken = ""
            }
            else -> {}
        }

        return response
    }
}