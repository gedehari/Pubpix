package com.gedehari.pubpix.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.gedehari.pubpix.databinding.ActivityLoginBinding
import com.gedehari.pubpix.network.ApiService
import com.google.android.material.snackbar.Snackbar
import com.haroldadmin.cnradapter.NetworkResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {
    private val scope = CoroutineScope(Dispatchers.Main)
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        scope.launch {
            when (val result = ApiService.getClient().getPosts()) {
                is NetworkResponse.Success -> {
                    Log.i("PubPix", result.body.toString())
                }
                else -> {
                    Snackbar.make(binding.root, "API or network error!", Snackbar.LENGTH_SHORT).show()
                }
            }
        }

        binding.loginButton.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}