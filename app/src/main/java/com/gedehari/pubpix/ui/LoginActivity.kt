package com.gedehari.pubpix.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.gedehari.pubpix.databinding.ActivityLoginBinding
import com.gedehari.pubpix.repo.AuthRepository
import com.gedehari.pubpix.ui.main.MainActivity
import com.google.android.material.textfield.TextInputLayout
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

        binding.errorText.visibility = View.GONE

        binding.loginButton.setOnClickListener {
            if (checkInput())
                scope.launch { tryLogin() }
        }

        binding.signUpButton.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }

    private fun checkInput(): Boolean {
        val usernameValid = !isTextFieldBlank(binding.usernameInput)
        val passwordValid = !isTextFieldBlank(binding.passwordInput)

        return usernameValid && passwordValid
    }

    private suspend fun tryLogin() {
        binding.apply {
            usernameInput.isEnabled = false
            passwordInput.isEnabled = false
            loginButton.isEnabled = false
            signUpButton.isEnabled = false
        }

        val response = AuthRepository.login(
            binding.usernameInput.editText!!.text!!.toString(),
            binding.passwordInput.editText!!.text!!.toString()
        )

        when (response) {
            is NetworkResponse.Success -> {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
            is NetworkResponse.Error -> {
                if (response is NetworkResponse.NetworkError)
                    setErrorText("Network error!")
                else
                    setErrorText("Error: ${response.body?.err}")
                binding.apply {
                    usernameInput.isEnabled = true
                    passwordInput.isEnabled = true
                    loginButton.isEnabled = true
                    signUpButton.isEnabled = true
                }
            }
        }
    }

    private fun setErrorText(errorText: String) {
        binding.errorText.apply {
            visibility = View.VISIBLE
            text = errorText
        }
    }

    private fun isTextFieldBlank(input: TextInputLayout): Boolean {
        if (input.editText!!.text!!.isBlank()) {
            input.error = "*Required"
            return true
        }

        input.error = null
        return false
    }
}