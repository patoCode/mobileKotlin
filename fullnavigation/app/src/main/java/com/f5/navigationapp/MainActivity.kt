package com.f5.navigationapp

import android.icu.lang.UCharacter.VerticalOrientation
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.f5.navigationapp.databinding.ActivityMainBinding
import com.f5.navigationapp.infraestructure.AppAdapter
import com.f5.navigationapp.infraestructure.AppAdapterOffer
import com.f5.navigationapp.infraestructure.MenuOption
import com.f5.navigationapp.infraestructure.Offer
import java.math.BigDecimal

class MainActivity : AppCompatActivity() {
    private lateinit var ui: ActivityMainBinding

    private var listOptions = listOf(
        MenuOption("#8DA795", "Hotel", R.drawable.icon_hotel),
        MenuOption("#E0BB8B", "Restaurant", R.drawable.icon_rest),
        MenuOption("#DC9668", "Taxi", R.drawable.icon_taxi),
        MenuOption("#B16A5B", "Aeroport", R.drawable.icon_plane),
        MenuOption("#64484A", "Gasoline", R.drawable.icon_gas),
    )

    private var listOptionOffer = listOf(
        Offer(BigDecimal("123.55"),"Bolivia", "Sucre", R.drawable.city),
        Offer(BigDecimal("123.55"),"Bolivia", "La Paz", R.drawable.city),
        Offer(BigDecimal("123.55"),"Bolivia", "Tarija", R.drawable.city),
        Offer(BigDecimal("123.55"),"Bolivia", "Oruro", R.drawable.city),
        Offer(BigDecimal("123.55"),"Bolivia", "Santa Cruz", R.drawable.city)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui = ActivityMainBinding.inflate(layoutInflater)
        setContentView(ui.root)

        ui.rvMenu.adapter = AppAdapter(listOptions)
        ui.rvMenu.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL ,false)

        ui.rvBestOffer.adapter = AppAdapterOffer(listOptionOffer)
        ui.rvBestOffer.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL ,false)
    }
}