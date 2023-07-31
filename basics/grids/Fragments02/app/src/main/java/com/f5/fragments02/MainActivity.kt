package com.f5.fragments02

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils.replace
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.f5.fragments02.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var ui: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui = ActivityMainBinding.inflate(layoutInflater)
        setContentView(ui.root)
        val fragment = PrimerFragment.newInstance()
        replaceFragment(fragment)

        ui.navMenu.setOnNavigationItemSelectedListener {
            Log.d("-FRAGMENT-", "SELECCIONADO: ${it.itemId}")
            when(it.itemId){
                R.id.item1 -> replaceFragment(PrimerFragment.newInstance())
                R.id.item2 -> replaceFragment(SegundoFragment.newInstance())
                R.id.item3 -> replaceFragment(TercerFragment.newInstance())
            }
            return@setOnNavigationItemSelectedListener true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.flContainer, fragment)
        fragmentTransaction.commit()
    }
}