package com.gedehari.pubpix.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gedehari.pubpix.databinding.ActivityLoginBinding
import com.gedehari.pubpix.ui.main.MainActivity
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class LoginActivity : AppCompatActivity() {
    private val scope = CoroutineScope(Dispatchers.Main)
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.loginButton.setOnClickListener {
            if (checkInput()) {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }

        binding.signUpButton.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }

    private fun checkInput(): Boolean {
        var usernameValid = !isTextFieldBlank(binding.usernameInput)
        var passwordValid = !isTextFieldBlank(binding.passwordInput)

        return usernameValid && passwordValid
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