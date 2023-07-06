package com.f5.udemyuber.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.f5.udemyuber.R
import com.f5.udemyuber.databinding.ActivityMapBinding

class MapActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMapBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}