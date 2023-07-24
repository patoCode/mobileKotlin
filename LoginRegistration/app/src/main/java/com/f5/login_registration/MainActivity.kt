package com.f5.login_registration

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.f5.login_registration.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var _ui: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _ui = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_ui.root)
    }
}