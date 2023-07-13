package com.f5.components

import android.app.Activity
import android.app.ProgressDialog.show
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.Gravity
import android.view.View
import android.widget.CompoundButton
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import com.f5.components.databinding.ActivityMainBinding
import com.google.android.material.checkbox.MaterialCheckBox

class MainActivity : AppCompatActivity() {

    private lateinit var _ui: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _ui = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_ui.root)

        val remember = _ui.chRemember
        remember.setOnCheckedChangeListener{ _, isCheck ->
            if(isCheck)
                Toast(this).showCustomToast("CHECK!!", this)
            else
                Toast(this).showCustomToast("NO CHECK!!", this)
        }

        _ui.btnToast.setOnClickListener {
            Toast(this).showCustomToast("NO CHECK!!", this)
        }
        _ui.btnLogin.setOnClickListener { login() }
    }


    fun Toast.showCustomToast(message: String, activity: Activity){
        val layout = activity.layoutInflater.inflate (R.layout.custom_toast,activity.findViewById(R.id.cvToast))
        val textView = layout.findViewById<TextView>(R.id.tvMessage)
        textView.text = message
        this.apply {
            duration = Toast.LENGTH_SHORT
            view = layout
            show()
        }
    }

    private fun showToast(msg: String, ctx: Context, layout: View){
        val customToastLayout = layout
        val customToast = Toast(ctx)
        customToast. setText(msg)
        customToast.view = customToastLayout
        customToast.setGravity(Gravity.CENTER, 0, 0)
        customToast.duration = Toast.LENGTH_LONG
        customToast.show()
    }

    private fun validPassword(): String?{
        val passwordText = _ui.tiPassword.text.toString()
        if(passwordText.length < 8) {
            return "Minimum 8 Character Password"
        }
        if(!passwordText.matches(".*[A-Z].*".toRegex())) {
            return "Must Contain 1 Upper-case Character"
        }
        if(!passwordText.matches(".*[a-z].*".toRegex())) {
            return "Must Contain 1 Lower-case Character"
        }
        if(!passwordText.matches(".*[@#\$%^&+=].*".toRegex())) {
            return "Must Contain 1 Special Character (@#\$%^&+=)"
        }

        return null
    }

    private fun checkIfEmailIsValid(): String? {
        var msg: String? = ""
        val emailInputText = _ui.tiEmail.text.toString()

        _ui.tiEmail.doOnTextChanged { text, start, before, count ->
            _ui.tiEmail.error = null
            if(!Patterns.EMAIL_ADDRESS.matcher(emailInputText).matches()){
                msg = "Invalid Email Address"
                _ui.tiEmail.error = msg
            }
        }
        return null
    }

    private fun login() {
        _ui.tilContainerEmail.helperText = checkIfEmailIsValid()
        _ui.tilContainerPassword.helperText = validPassword()


        val checkIfEmailIsValid =  _ui.tilContainerEmail.helperText == null
        val validPassword = _ui.tilContainerPassword.helperText == null

        if (checkIfEmailIsValid && validPassword) {
            Toast.makeText(this,"Valid Form", Toast.LENGTH_LONG).show()
            resetForm()
        } else {
            Toast.makeText(this, "Invalid Form", Toast.LENGTH_LONG).show()
        }
    }

    private fun resetForm() {
        _ui.tiEmail.text = null
        _ui.tiPassword.text = null
        _ui.tilContainerEmail.helperText = ""
        _ui.tilContainerPassword.helperText = ""
    }


}