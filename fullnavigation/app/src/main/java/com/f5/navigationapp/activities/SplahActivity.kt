package com.f5.navigationapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.f5.navigationapp.R
import com.f5.navigationapp.databinding.ActivitySplahBinding

class SplahActivity : AppCompatActivity() {
    private lateinit var ui: ActivitySplahBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui = ActivitySplahBinding.inflate(layoutInflater)
        setContentView(ui.root)
    }
}