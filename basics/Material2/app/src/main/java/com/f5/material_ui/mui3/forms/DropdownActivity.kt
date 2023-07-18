package com.f5.material_ui.mui3.forms

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.f5.material_ui.R
import com.f5.material_ui.databinding.ActivityDropdownBinding

class DropdownActivity : AppCompatActivity() {
    private lateinit var _ui: ActivityDropdownBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _ui = ActivityDropdownBinding.inflate(layoutInflater)
        setContentView(_ui.root)
    }
}