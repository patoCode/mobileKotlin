package com.f5.login_registration.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.f5.login_registration.R
import com.f5.login_registration.databinding.ActivityRegisterBinding

class Register : AppCompatActivity() {

    private lateinit var ui: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(ui.root)

        ui.btnRegister.setOnClickListener {
            val email = ui.tieEmail.text.toString()
            val pasword = ui.tiePassword.text.toString()

        }

    }
}