package com.f5.material_ui.mui3.forms

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.f5.material_ui.R
import com.f5.material_ui.databinding.ActivityDatepickerBinding

class DatepickerActivity : AppCompatActivity() {
    lateinit var _ui: ActivityDatepickerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _ui = ActivityDatepickerBinding.inflate(layoutInflater)
        setContentView(_ui.root)
    }
}