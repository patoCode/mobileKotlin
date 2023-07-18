package com.f5.material_ui.mui3.forms

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.f5.material_ui.R
import com.f5.material_ui.databinding.ActivityButtonBinding

class ButtonActivity : AppCompatActivity() {
    val TAG = "BUTTONS"
    private lateinit var _ui: ActivityButtonBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _ui = ActivityButtonBinding.inflate(layoutInflater)
        setContentView(_ui.root)

        _ui.mbtToggle.addOnButtonCheckedListener { group, checkedId, isChecked ->
            Log.d(TAG, "GRUPO: ${group.toString()} > CHECK_ID: ${checkedId.toString()} > IS_CHECK: ${isChecked}")
            when(checkedId){
                R.id.button1 -> Toast.makeText(this@ButtonActivity, "${R.id.button1} CHECK", Toast.LENGTH_SHORT).show()
                R.id.button2 -> Toast.makeText(this@ButtonActivity, "${R.id.button2} CHECK", Toast.LENGTH_SHORT).show()
                R.id.button3 -> Toast.makeText(this@ButtonActivity, "${R.id.button3} CHECK", Toast.LENGTH_SHORT).show()
                else -> Toast.makeText(this@ButtonActivity, "ELSE CHECK", Toast.LENGTH_LONG).show()
            }
        }

        // Floating Button
        _ui.fbButton.setOnClickListener {
            Toast.makeText(this, "CLICK FLOATING BUTTON", Toast.LENGTH_SHORT).show()
        }

    }
}