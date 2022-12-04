package com.gedehari.pubpix.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.gedehari.pubpix.R
import java.time.Instant

class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        supportActionBar?.hide()

        Log.i("PubPix", Instant.now().toString())

        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
}