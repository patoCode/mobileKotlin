package com.f5.planto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.f5.planto.databinding.ActivityPlanetBinding

class PlanetActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPlanetBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlanetBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}