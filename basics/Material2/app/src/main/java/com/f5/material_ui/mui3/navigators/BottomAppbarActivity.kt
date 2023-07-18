package com.f5.material_ui.mui3.navigators

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.f5.material_ui.R
import com.f5.material_ui.mui3.ActivityBase

class BottomAppbarActivity : AppCompatActivity(), ActivityBase {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_appbar)
    }
}