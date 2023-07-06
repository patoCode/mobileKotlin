package com.f5.udemyuber.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import com.f5.udemyuber.databinding.ActivityRegistryBinding
import com.f5.udemyuber.providers.Auth
import com.f5.udemyuber.providers.Client
import com.f5.udemyuber.models.Client as mClient

class RegistryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistryBinding
    private val authProvider = Auth()
    private val clientProvider = Client()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegistryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        binding.btnLogin.setOnClickListener { goToLogin() }

        binding.btnRegistry.setOnClickListener { registry() }

    }

    private fun registry(){
        val name = binding.tieName.text.toString().trim()
        val lastName = binding.tieLastName.text.toString().trim()
        val phone = binding.tiePhone.text.toString().trim()
        val username = binding.tieUsername.text.toString().trim()
        val password = binding.tiePassword.text.toString().trim()
        val confirm = binding.tieConfirm.text.toString().trim()

        if(validForm(name, lastName, phone, username, password, confirm)){
            authProvider.register(username, password).addOnCompleteListener {
                if(it.isSuccessful){
                    val _client = mClient(
                        id = authProvider.getId(),
                        name = name,
                        lastname = lastName,
                        phone = phone,
                        username = username
                    )
                    clientProvider.create(_client).addOnCompleteListener {
                        if(it.isSuccessful){
                            Log.d("- ERROR -","!!!Registro exitoso!!!")
                            Toast.makeText(this@RegistryActivity, "!!!EXITO!!!", Toast.LENGTH_LONG).show()
                            goToMap()
                        } else {
                            Toast.makeText(this@RegistryActivity, "!!!EROR!!! ${it.exception.toString()}", Toast.LENGTH_LONG).show()
                            Log.d("- ERROR -","!!!EROR!!! ${it.exception.toString()}")
                        }
                    }
                } else {
                    Toast.makeText(this@RegistryActivity, "!!!EROR!!! ${it.exception.toString()}", Toast.LENGTH_LONG).show()
                    Log.d("- ERROR -","!!!EROR!!! ${it.exception.toString()}")
                }
            }
            Toast.makeText(this, " !!!REGISTRY!!! ", Toast.LENGTH_LONG).show()
        }

    }

    private fun validForm(name: String,
                          lastName: String,
                          phone: String,
                          username: String,
                          password: String,
                          confirm: String): Boolean{
        if(name.isEmpty()){
            Toast.makeText(this, "Invalid name", Toast.LENGTH_LONG).show()
            return false
        }
        if(lastName.isEmpty()){
            Toast.makeText(this, "Invalid last name", Toast.LENGTH_LONG).show()
            return false
        }
        if(phone.isEmpty()){
            Toast.makeText(this, "Invalid phone", Toast.LENGTH_LONG).show()
            return false
        }
        if(username.isEmpty()){
            Toast.makeText(this, "Invalid username", Toast.LENGTH_LONG).show()
            return false
        }
        if(password.isEmpty()){
            Toast.makeText(this, "Invalid password", Toast.LENGTH_LONG).show()
            return false
        }
        if(password.length < 6){
            Toast.makeText(this, "Invalid password. Min 6 characters is required", Toast.LENGTH_LONG).show()
            return false
        }
        if(!password.equals(confirm)){
            Toast.makeText(this, "Password(s) not matching.", Toast.LENGTH_LONG).show()
            return false
        }
        return true
    }

    private fun goToMap(){
        val i = Intent(this, MapActivity::class.java)
        i.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(i)
    }

    private fun goToLogin() {
        val i = Intent(this, MainActivity::class.java)
        startActivity(i)
    }
}