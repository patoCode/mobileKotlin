package com.f5.sharedpreferences

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import com.f5.sharedpreferences.databinding.ActivityMainBinding
import com.f5.sharedpreferences.sp_client.SharedClient
import com.f5.sharedpreferences.sp_client.SharedClient.userId

class MainActivity : AppCompatActivity() {
    private lateinit var ui: ActivityMainBinding
    val sp = SharedClient.customPreferences(this, "Prueba")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui = ActivityMainBinding.inflate(layoutInflater)
        setContentView(ui.root)


        ui.tvValue.setText(sp.userId.toString())
        ui.btnSave.setOnClickListener {
            sp.userId = ui.tvValue.text.toString().toInt()
        }

        ui.sw01.setOnCheckedChangeListener { compoundButton, b ->
            if(ui.sw01.isChecked)
                sp.userId = 5
            else
                sp.userId = 0
        }
//        verifySP()
    }

    private fun verifySP() {
        ui.sw01.isChecked = sp.userId > 0
    }
}