package com.f5.login_registration.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import com.f5.login_registration.MainActivity
import com.f5.login_registration.R
import com.f5.login_registration.databinding.ActivityLoginBinding
import com.f5.login_registration.providers.AuthProvider
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class Login : AppCompatActivity() {
    private val TAG = "-MAIN_ACT-"

    private lateinit var _ui: ActivityLoginBinding
    var authProvider = AuthProvider()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _ui = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(_ui.root)

        _ui.btnLogin.setOnClickListener {login()}
        _ui.btnSignGoogle.setOnClickListener{ googleSign() }
        _ui.btnRegister.setOnClickListener {goToRegistry()}

    }

    private fun googleSign() {
        var googleIntent = authProvider.loginGoogle(this).signInIntent
        startActivityForResult(googleIntent, 10001)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.i(TAG, requestCode.toString())
        if(requestCode == 10001 ){
            try {
                val task = GoogleSignIn.getSignedInAccountFromIntent(data)
                val account = task.getResult(ApiException::class.java)
                val credential  = GoogleAuthProvider.getCredential(account.idToken, null)
                FirebaseAuth.getInstance().signInWithCredential(credential).addOnCompleteListener {
                    if(it.isSuccessful){
                        Toast
                            .makeText(
                                this@Login,
                                "!!!LOGIN CORRECTO!!! BIENVENIDO",
                                Toast.LENGTH_LONG
                            )
                            .show()
                        gotToMain("GOOGLE")
                    }else{
                        Toast.makeText(this@Login, "!!!EROR!!! ${it.exception.toString()}", Toast.LENGTH_LONG).show()
                        Log.d("- ERROR -","!!!EROR!!! ${it.exception.toString()}")
                    }
                }
            } catch(e: Exception){
                Log.d(TAG, "EXCEPTION ${e.toString()}")
            }

        }
    }


    fun login(){
        var username = _ui.tieEmail.text.toString()
        var password = _ui.tiePassword.text.toString()
        authProvider.login(username, password).addOnCompleteListener {
            if(it.isSuccessful){
                Toast.makeText(this@Login, "!!!LOGIN CORRECTO!!! BIENVENIDO ${username}", Toast.LENGTH_LONG).show()
                gotToMain(username)
            } else {
                Toast.makeText(this@Login, "!!!EROR!!! ${it.exception.toString()}", Toast.LENGTH_LONG).show()
                Log.d("- ERROR -","!!!EROR!!! ${it.exception.toString()}")
            }
        }
    }

    private fun gotToMain(username: String) {
        val i = Intent(this, MainActivity::class.java)
        // i.putExtra("username",username)
        startActivity(i)
    }

    fun goToRegistry(){
        val i = Intent(this, Register::class.java)
        startActivity(i)
    }
}