package com.f5.login_sharedpreferences.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.f5.login_sharedpreferences.R
import com.f5.login_sharedpreferences.databinding.ActivityLoginBinding
import com.f5.login_sharedpreferences.infraestructure.SessionManager
import com.f5.login_sharedpreferences.models.User

class LoginActivity : AppCompatActivity() {

    private lateinit var _ui: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _ui = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(_ui.root)
    }

    override fun onStart() {
        super.onStart()
        checkSession()
    }

    private fun checkSession() {
        val sessionManager = SessionManager(this)
        var userId = sessionManager.getSession()
        if(userId != -1)
            moveActivity()
    }

    fun login(view: View) {
        val user = User(1, "patoCode")
        val sessionManager = SessionManager(this@LoginActivity)
        sessionManager.saveSession(user)
        moveActivity()
    }

    fun moveActivity() {
        val i = Intent(this@LoginActivity, MainActivity::class.java)
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(i)
    }

}