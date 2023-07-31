package com.f5.crud_sp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.f5.crud_sp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var ui: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui = ActivityMainBinding.inflate(layoutInflater)
        setContentView(ui.root)
    }
}