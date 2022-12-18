package com.gedehari.pubpix.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.gedehari.pubpix.R
import com.gedehari.pubpix.databinding.ActivitySignUpBinding
import com.gedehari.pubpix.repo.AuthRepository
import com.gedehari.pubpix.ui.main.MainActivity
import com.google.android.material.textfield.TextInputLayout
import com.haroldadmin.cnradapter.NetworkResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignUpActivity : AppCompatActivity() {
    private val scope = CoroutineScope(Dispatchers.Main)
    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.signupButton.setOnClickListener {
            if (checkInput())
                scope.launch { trySignUp() }
        }

        binding.cancelButton.setOnClickListener {
            finish()
        }
    }

    private fun checkInput(): Boolean {
        val usernameValid = !isTextFieldBlank(binding.usernameInput)
        val displayNameValid = !isTextFieldBlank(binding.displayNameInput)
        val passwordValid = !isTextFieldBlank(binding.passwordInput)
        val repeatPasswordValid = !isTextFieldBlank(binding.repeatPasswordInput)

        if (usernameValid && displayNameValid && passwordValid && repeatPasswordValid) {
            if (binding.passwordInput.editText!!.text!!.toString()
                == binding.repeatPasswordInput.editText!!.text!!.toString())
                return true
            else
                binding.repeatPasswordInput.error = "Password mismatch"
        }
        return false
    }

    private suspend fun trySignUp() {
        binding.apply {
            usernameInput.isEnabled = false
            displayNameInput.isEnabled = false
            passwordInput.isEnabled = false
            repeatPasswordInput.isEnabled = false
            signupButton.isEnabled = false
            cancelButton.isEnabled = false
        }

        val response = AuthRepository.signUp(
            binding.usernameInput.editText!!.text!!.toString(),
            binding.displayNameInput.editText!!.text!!.toString(),
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
                    usernameInput.isEnabled = false
                    displayNameInput.isEnabled = false
                    passwordInput.isEnabled = false
                    repeatPasswordInput.isEnabled = false
                    signupButton.isEnabled = false
                    cancelButton.isEnabled = false
                }
            }
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

    private fun setErrorText(errorText: String) {
        binding.errorText.apply {
            visibility = View.VISIBLE
            text = errorText
        }
    }
}