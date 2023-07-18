package com.f5.templates_login.logins

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import com.f5.templates_login.MainActivity
import com.f5.templates_login.R
import com.f5.templates_login.databinding.ActivityAloginBinding

class ALoginActivity : AppCompatActivity() {
    private lateinit var _ui : ActivityAloginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _ui = ActivityAloginBinding.inflate(layoutInflater)
        setContentView(_ui.root)
        window.decorView.apply { systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN }
        Handler(Looper.getMainLooper()).postDelayed({
            val i = Intent(this, OrangeActivity::class.java)
            startActivity(i)
        }, 500)
    }



}