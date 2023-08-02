package com.f5.crud_sp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.f5.crud_sp.R
import com.f5.crud_sp.databinding.ActivityMainBinding
import com.f5.crud_sp.fragments.CreateFragment
import com.f5.crud_sp.fragments.ReadFragment
import com.f5.crud_sp.fragments.SettingsFragment

class MainActivity : AppCompatActivity() {
    private lateinit var ui: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui = ActivityMainBinding.inflate(layoutInflater)
        setContentView(ui.root)

        replaceFragment(SettingsFragment.newInstance())

        ui.bnvNavigator.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.optCrear -> replaceFragment(CreateFragment.newInstance())
                R.id.optRead -> replaceFragment(ReadFragment.newInstance())
                R.id.optUpdate -> replaceFragment(CreateFragment.newInstance())
                R.id.optDelete -> replaceFragment(CreateFragment.newInstance())
                else -> replaceFragment(SettingsFragment.newInstance())
            }
            return@setOnNavigationItemSelectedListener true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentTransacition = supportFragmentManager.beginTransaction()
        fragmentTransacition.replace(R.id.fcvContainer, fragment)
        fragmentTransacition.commit()
    }
}