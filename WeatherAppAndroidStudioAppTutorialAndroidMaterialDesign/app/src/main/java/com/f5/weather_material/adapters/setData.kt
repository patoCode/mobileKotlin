package com.f5.weather_material.adapters

import com.f5.weather_material.domains.Hourly

object setData {
    fun setHourly(): List<Hourly>{
        var list = mutableListOf<Hourly>()
        list.add(Hourly("15:00 pm", 25, "cloudy"))
        list.add(Hourly("16:00 pm", 25, "cloudy_sunny"))
        list.add(Hourly("17:00 pm", 20, "sun"))
        list.add(Hourly("18:00 pm", 20, "storm"))
        list.add(Hourly("19:00 pm", 20, "snowy"))
        return list;
    }

}