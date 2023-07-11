package com.f5.weather_material.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.f5.weather_material.R
import com.f5.weather_material.databinding.ViewItemBinding
import com.f5.weather_material.domains.Hourly

class HourlyAdapter(private val list: List<Hourly>): RecyclerView.Adapter<MyViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ViewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var dummyImage: Int? = null
        var ui = holder.binding

        ui.tvHour.text = list[position].hour
        ui.tvTemperature.text = list[position].temp.toString()

        when (list[position].pathImage.toString().toLowerCase()){
            "sun" -> dummyImage = R.drawable.sun
            "cloudy_sunny" -> dummyImage = R.drawable.cloudy_sunny
            "cloudy" -> dummyImage = R.drawable.cloudy
            "storm" -> dummyImage = R.drawable.storm
            "snowy" -> dummyImage = R.drawable.snowy
            else ->  dummyImage = R.drawable.rainy
        }
        ui.imageView.setImageResource(dummyImage)

    }
}