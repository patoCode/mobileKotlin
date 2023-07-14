package com.f5.login.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.f5.login.R
import com.f5.login.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var _ui: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _ui = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_ui.root)
    }
}