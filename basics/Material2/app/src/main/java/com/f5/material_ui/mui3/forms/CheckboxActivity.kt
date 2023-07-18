package com.f5.material_ui.mui3.forms

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.f5.material_ui.R
import com.f5.material_ui.databinding.ActivityCheckboxBinding

class CheckboxActivity : AppCompatActivity() {
    private val TAG = "-CHECKS-"
    private lateinit var _ui: ActivityCheckboxBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _ui = ActivityCheckboxBinding.inflate(layoutInflater)
        setContentView(_ui.root)

        _ui.check02.isChecked = true
        _ui.check02.setOnCheckedChangeListener { btnView, isCheck ->
            if(isCheck)
                Toast.makeText(this, "${btnView.toString()} > ${isCheck.toString()}", Toast.LENGTH_SHORT).show()
        }

        // Chips Actions
        // CLICK
        _ui.chip01.setOnClickListener {
            Toast.makeText(this, "Se clickeo el chip", Toast.LENGTH_SHORT).show()
        }
        // GROUP
        _ui.cgGroup.setOnCheckedChangeListener { group, checkedId ->
            Log.d(TAG, group.toString())
            when(checkedId){
                R.id.chipA -> Toast.makeText(this, "Se clickeo el chip ${checkedId.toString()}", Toast.LENGTH_SHORT).show()
                else -> Toast.makeText(this, "DEFAULT SELECTION", Toast.LENGTH_SHORT).show()
            }

        }

    }
}