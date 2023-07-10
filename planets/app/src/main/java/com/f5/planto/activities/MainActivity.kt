package com.f5.planto.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.f5.planto.R
import com.f5.planto.assets.recycler.Adapter
import com.f5.planto.assets.recycler.setData
import com.f5.planto.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.decorView.apply { systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN }

        binding.rvMain.adapter = Adapter(setData.SetPlanets())
        binding.rvMain.layoutManager = LinearLayoutManager(this)



    }
}