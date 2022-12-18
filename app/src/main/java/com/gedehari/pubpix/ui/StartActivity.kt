package com.gedehari.pubpix.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gedehari.pubpix.R
import com.gedehari.pubpix.database.AppDatabase
import com.gedehari.pubpix.repo.PreferenceRepository
import com.gedehari.pubpix.repo.AuthRepository
import com.gedehari.pubpix.ui.main.MainActivity
import com.haroldadmin.cnradapter.NetworkResponse
import kotlinx.coroutines.runBlocking

class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        supportActionBar?.hide()
        
        AppDatabase.initialize(this)
        PreferenceRepository.sharedPreferences = getSharedPreferences(PreferenceRepository.APP_KEY, Context.MODE_PRIVATE)
        runBlocking { checkIfLoggedIn() }
    }

    private suspend fun checkIfLoggedIn() {
        if (PreferenceRepository.refreshToken!!.isNotBlank()) {
            when (AuthRepository.refresh()) {
                is NetworkResponse.Success -> {
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                    return
                }
                else -> {
                    AppDatabase.getInstance().postDao().obliterate()
                }
            }
        }

        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
}