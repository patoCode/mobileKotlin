package com.f5.material_ui.mui3.navigators

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.f5.material_ui.R
import com.f5.material_ui.databinding.ActivityAppbarBinding
import com.f5.material_ui.databinding.ActivityBottomAppbarBinding
import com.f5.material_ui.databinding.ActivityBottomNavBinding
import com.f5.material_ui.mui3.ActivityBase

class BottomNavActivity : AppCompatActivity(), ActivityBase {
    private lateinit var _ui: ActivityBottomNavBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _ui = ActivityBottomNavBinding.inflate(layoutInflater)
        setContentView(_ui.root)
    }
}