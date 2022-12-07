package com.gedehari.pubpix.repo

import android.content.SharedPreferences

object PreferenceRepository {
    const val APP_KEY = "com.gedehari.pubpix.PREFERENCES"
    const val ACCESS_TOKEN_KEY = "ACCESS_TOKEN"
    const val REFRESH_TOKEN_KEY = "REFRESH_TOKEN"

    lateinit var sharedPreferences: SharedPreferences

    var accessToken: String?
        get() = sharedPreferences.getString(ACCESS_TOKEN_KEY, "")
        set(value) = with(sharedPreferences.edit()) {
            putString(ACCESS_TOKEN_KEY, value)
            apply()
        }

    var refreshToken: String?
        get() = sharedPreferences.getString(REFRESH_TOKEN_KEY, "")
        set(value) = with(sharedPreferences.edit()) {
            putString(REFRESH_TOKEN_KEY, value)
            apply()
        }
}