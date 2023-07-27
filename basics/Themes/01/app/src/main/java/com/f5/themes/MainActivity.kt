package com.f5.themes


import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import com.f5.themes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val TAG = "-THEME-"
    private lateinit var ui: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui = ActivityMainBinding.inflate(layoutInflater)
        setContentView(ui.root)

        verifyTheme()

        ui.swcTheme.setOnCheckedChangeListener { compoundButton, b ->
            if(ui.swcTheme.isChecked){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }

    }

    private fun verifyTheme() {
        when(resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK){
            Configuration.UI_MODE_NIGHT_YES -> {
                ui.swcTheme.isChecked = true
                ui.swTheme.isChecked = true
                ui.swTheme.text = "Activate Ligth Mode"
                ui.ivMode.setImageResource(R.drawable.app_icon_c_sun)
            }
            Configuration.UI_MODE_NIGHT_NO ->{
                ui.swcTheme.isChecked = false
                ui.swTheme.isChecked = false
                ui.swTheme.text = "Activate Nigth Mode"
                ui.ivMode.setImageResource(R.drawable.app_icon_c_moon)
            }
        }
    }
}