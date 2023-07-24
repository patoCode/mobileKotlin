package com.f5.login_registration.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.f5.login_registration.R
import com.f5.login_registration.databinding.ActivityLoginBinding

class Login : AppCompatActivity() {
    private lateinit var _ui: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _ui = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(_ui.root)
    }
}