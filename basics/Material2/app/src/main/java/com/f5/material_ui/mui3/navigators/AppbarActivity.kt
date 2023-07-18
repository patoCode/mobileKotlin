package com.f5.material_ui.mui3.navigators

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.f5.material_ui.databinding.ActivityAppbarBinding
import com.f5.material_ui.mui3.ActivityBase

class AppbarActivity : AppCompatActivity(), ActivityBase  {
    private lateinit var _ui: ActivityAppbarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _ui = ActivityAppbarBinding.inflate(layoutInflater)
        setContentView(_ui.root)
    }
}