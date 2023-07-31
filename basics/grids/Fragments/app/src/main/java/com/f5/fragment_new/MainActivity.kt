package com.f5.fragment_new

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.f5.fragment_new.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var ui: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui = ActivityMainBinding.inflate(layoutInflater)
        setContentView(ui.root)

        ui.btnFragment01.setOnClickListener {
            replaceFragment(Fragment1())
        }

        ui.btnFragment02.setOnClickListener {
            replaceFragment(Fragment2())
        }

    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTrasaction = fragmentManager.beginTransaction()
        fragmentTrasaction.replace(R.id.fcvMain, fragment)
        fragmentTrasaction.commit()
    }
}