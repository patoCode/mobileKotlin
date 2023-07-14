package com.f5.login_sharedpreferences.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.f5.login_sharedpreferences.R
import com.f5.login_sharedpreferences.databinding.ActivityMainBinding
import com.f5.login_sharedpreferences.infraestructure.SessionManager

class MainActivity : AppCompatActivity() {
    private lateinit var _ui: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _ui = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_ui.root)
    }

    fun logout(view: View) {
        val sessionManager = SessionManager(this)
        sessionManager.removeSession()
        moveToLogin()
    }

    private fun moveToLogin() {
        val i = Intent(this@MainActivity, LoginActivity::class.java)
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(i)
    }
}