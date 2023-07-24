package com.f5.material_ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import com.f5.material_ui.databinding.ActivityMainBinding
import com.f5.material_ui.mui3.containers.MenuContainerActivity
import com.f5.material_ui.mui3.forms.MenuFormActivity
import com.f5.material_ui.mui3.navigators.BottomNavActivity
import com.f5.material_ui.mui3.navigators.MenuNavigatorActivity
import com.f5.material_ui.mui3.widgets.MenuWidgetActivity

class MainActivity : AppCompatActivity() {

    private lateinit var _ui : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _ui = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_ui.root)


        _ui.btn01.setOnClickListener {
            val i = Intent(this, MenuNavigatorActivity::class.java)
            startActivity(i)
        }

        _ui.btn02.setOnClickListener {
            val i = Intent(this, MenuFormActivity::class.java)
            startActivity(i)
        }

        _ui.btn03.setOnClickListener {
            val i = Intent(this, MenuContainerActivity::class.java)
            startActivity(i)
        }

        _ui.btn04.setOnClickListener {
            val i = Intent(this, MenuWidgetActivity::class.java)
            startActivity(i)
        }

    }
}