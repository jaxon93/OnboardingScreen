package com.example.onboardingscreenrecyclerviewkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.onboardingscreenrecyclerviewkotlin.databinding.ActivityGetStartedBinding

class GetStarted : AppCompatActivity() {
    lateinit var startButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityGetStartedBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.startButton.setOnClickListener(View.OnClickListener {
            val i = Intent(this@GetStarted, com.example.onboardingscreenrecyclerviewkotlin.MainActivity::class.java)
            startActivity(i)
            finish()
        })
    }
}