package com.f5.material_ui.mui3.forms

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.f5.material_ui.databinding.ActivityMenuButtonBinding
import com.f5.material_ui.recycler.Adapter
import com.f5.material_ui.recycler.MuiComponent
import com.f5.material_ui.recycler.setData

class MenuFormActivity : AppCompatActivity() {
    private lateinit var _ui: ActivityMenuButtonBinding
    private lateinit var adapter: Adapter
    private val componentList = mutableListOf<MuiComponent>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _ui = ActivityMenuButtonBinding.inflate(layoutInflater)
        setContentView(_ui.root)
        window.decorView.apply { systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN }

        initRecycler()
    }

    private fun initRecycler() {
        val click = Adapter.OnClickListener{ it ->
            try {
                val _class = Class.forName(it.activity)
                val i = Intent(applicationContext, _class)
                startActivity(i)
            } catch (e: Exception){
                Log.d("Exception", e.message.toString())
            }
        }
        adapter = Adapter(setData.fillFormElements(), click)
        _ui.rvMain.layoutManager = LinearLayoutManager(this)
        _ui.rvMain.adapter = adapter

    }
}