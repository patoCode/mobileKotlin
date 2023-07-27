package com.f5.login_registration.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.f5.login_registration.MainActivity
import com.f5.login_registration.R
import com.f5.login_registration.databinding.ActivityRegisterBinding
import com.f5.login_registration.providers.AuthProvider

class Register : AppCompatActivity() {
    private lateinit var ui: ActivityRegisterBinding
    private var authProvider = AuthProvider()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(ui.root)
        ui.btnRegister.setOnClickListener { register() }
        ui.btnLogin.setOnClickListener { gotToLogin() }
    }

    private fun register(){
        val email = ui.tieEmail.text.toString()
        val password = ui.tiePassword.text.toString()
        authProvider.register(email, password).addOnCompleteListener {
            if(it.isSuccessful){
                Toast.makeText(this@Register, "!!!REGISTRO CORRECTO!!!", Toast.LENGTH_LONG).show()
                gotToMain()
            } else {
                Toast.makeText(this@Register, "!!!EROR!!! ${it.exception.toString()}", Toast.LENGTH_LONG).show()
                Log.d("- ERROR -","!!!EROR!!! ${it.exception.toString()}")
            }
        }
    }

    private fun gotToMain() {
        val i = Intent(this, MainActivity::class.java)
        startActivity(i)
    }

    private fun gotToLogin() {
        val i = Intent(this, Login::class.java)
        startActivity(i)
    }
}