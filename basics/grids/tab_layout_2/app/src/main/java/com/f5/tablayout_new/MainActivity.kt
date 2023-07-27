package com.f5.tablayout_new

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.f5.tablayout_new.databinding.ActivityMainBinding
import com.f5.tablayout_new.viewpager.Adapter
import com.f5.tablayout_new.viewpager.model.PagerModel

class MainActivity : AppCompatActivity() {
    private lateinit var ui: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui = ActivityMainBinding.inflate(layoutInflater)
        setContentView(ui.root)
        initPager()
    }

    private fun initPager() {
        var list = listOf(
            PagerModel(R.drawable.pic_001, R.color.colorPrimary, "Lorem 01", "But I must explain to you how all this mistaken idea of denouncing of a pleasure and praising pain was born and I will give you a complete account of the system"),
            PagerModel(R.drawable.pic_002, R.color.colorSecondary, "Lorem 02", "But I must explain to you how all this mistaken idea of denouncing of a pleasure and praising pain was born and I will give you a complete account of the system"),
            PagerModel(R.drawable.pic_003, R.color.colorAccent, "Lorem 03", "But I must explain to you how all this mistaken idea of denouncing of a pleasure and praising pain was born and I will give you a complete account of the system"),
            PagerModel(R.drawable.pic_004, R.color.colorNeutral, "Lorem 04", "But I must explain to you how all this mistaken idea of denouncing of a pleasure and praising pain was born and I will give you a complete account of the system"),
        )
        ui.vpMain.adapter = Adapter(list)
    }
}