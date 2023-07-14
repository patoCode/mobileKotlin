package com.f5.loginsp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import com.f5.loginsp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val TAG = "-APP-"
    private lateinit var _ui: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _ui = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_ui.root)

        val toolbar = _ui.mtMain
        toolbar.setNavigationOnClickListener{
            Log.d(TAG, "CLICKED ${it.toString()}")
        }

        toolbar.setOnMenuItemClickListener { menu ->
            when(menu.itemId){
                R.id.itMenu01 ->{
                    Log.d(TAG, "CLICKED 01")
                    true
                }
                R.id.itMenu02 ->{
                    Log.d(TAG, "CLICKED 02")
                    true
                }
                R.id.itMenu03 ->{
                    Log.d(TAG, "CLICKED 03")
                    true
                }
                else -> false
            }
        }

    }
}