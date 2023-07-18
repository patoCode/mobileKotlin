package com.f5.templates_login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.f5.templates_login.databinding.ActivityAloginBinding
import com.f5.templates_login.databinding.ActivityMainBinding
import com.f5.templates_login.logins.ALoginActivity

class MainActivity : AppCompatActivity() {
    private lateinit var _ui : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _ui = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_ui.root)

        _ui.btnOrange.setOnClickListener { it ->
            val i = Intent(this, ALoginActivity::class.java)
            startActivity(i)
        }

    }
}