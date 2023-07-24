package com.f5.firebase_store

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.f5.firebase_store.databinding.ActivityMainBinding
import com.f5.firebase_store.models.User
import com.f5.firebase_store.service.FBUser
import java.util.UUID


class MainActivity : AppCompatActivity() {
    private lateinit var _ui: ActivityMainBinding
    private var _db = FBUser()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _ui = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_ui.root)

        _ui.btnRegister.setOnClickListener {
            val id  = UUID.randomUUID().toString()
            val firstName = _ui.etName.text.toString()
            val lastName = _ui.etLastName.text.toString()
            val age = _ui.etAge.text.toString().toInt()
            val username = _ui.etUsername.text.toString()
            val _user = User(id, firstName, lastName, age, username)
            _db.create(_user).addOnCompleteListener {
                if(it.isSuccessful){
                    Toast.makeText(this@MainActivity, "Guardado Correcto!", Toast.LENGTH_SHORT).show()
                    clearInputs()
                } else {
                    Toast.makeText(this@MainActivity, "!!!EROR!!! ${it.exception.toString()}", Toast.LENGTH_LONG).show()
                    Log.d("- ERROR -","!!!EROR!!! ${it.exception.toString()}")
                }
            }
        }
        hiddenBars()
    }

    private fun clearInputs() {
        _ui.etAge.setText("")
        _ui.etName.setText("")
        _ui.etLastName.setText("")
        _ui.etUsername.setText("")
    }

    private fun hiddenBars(){
        val uiOptions = (View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN)
        window.decorView.setSystemUiVisibility(uiOptions)
    }

}