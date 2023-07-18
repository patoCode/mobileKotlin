package com.f5.material_ui.mui3.navigators

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.f5.material_ui.databinding.ActivityMenuNavigatorBinding
import com.f5.material_ui.recycler.Adapter
import com.f5.material_ui.recycler.MuiComponent
import com.f5.material_ui.recycler.setData

class MenuNavigatorActivity : AppCompatActivity() {

    private lateinit var _ui: ActivityMenuNavigatorBinding
    private lateinit var adapter: Adapter
    private val componentList = mutableListOf<MuiComponent>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.apply { systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN }
        _ui = ActivityMenuNavigatorBinding.inflate(layoutInflater)
        setContentView(_ui.root)
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
        adapter = Adapter(setData.fillNavigators(), click)
        _ui.rvMain.layoutManager = LinearLayoutManager(this)
        _ui.rvMain.adapter = adapter

    }
}