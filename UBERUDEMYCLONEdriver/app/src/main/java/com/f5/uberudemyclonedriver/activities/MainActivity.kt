package com.f5.uberudemyclonedriver.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import com.f5.uberudemyclonedriver.databinding.ActivityMainBinding
import com.f5.uberudemyclonedriver.providers.Auth

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val authProvider = Auth()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        binding.btnRegistry.setOnClickListener { goToRegistry() }
        binding.btnLogin.setOnClickListener { login() }

    }

    private fun login(){

        val username = binding.tieUsername.text.toString()
        val password = binding.tiePassword.text.toString()

        if(validateForm(username, password)){
            authProvider.login(username, password).addOnCompleteListener {
                if(it.isSuccessful){

                    goToMap()
                } else {
                    Toast.makeText(this@MainActivity, "!!! ERROR AL INICIAR SESION !!!", Toast.LENGTH_LONG).show()
                    Log.d("LOGIN-ERROR", "ERROR ${it.exception.toString()}")
                }
            }
        }
    }

    private fun validateForm(username:String, password:String): Boolean{

        if(username.isEmpty()){
            Toast.makeText(this, "Ingresa tu nombre de usuario", Toast.LENGTH_LONG).show()
            return false
        }
        if(password.isEmpty()){
            Toast.makeText(this, "Ingresa tu password", Toast.LENGTH_LONG).show()
            return false
        }
        return true
    }

    private fun goToMap(){
        val i = Intent(this, MapActivity::class.java)
        i.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(i)
    }

    private fun goToRegistry(){
        val i = Intent(this, RegistryActivity::class.java)
        startActivity(i)
    }

    override fun onStart() {
        super.onStart()
        if (authProvider.isLogged()) goToMap()
    }
}