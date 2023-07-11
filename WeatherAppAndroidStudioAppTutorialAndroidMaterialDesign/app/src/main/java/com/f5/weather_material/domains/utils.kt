package com.f5.weather_material.domains

import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.json.JSONObject

object utils {

    fun jsonToListForWeek(data: String){
        val timelines = JSONObject(data).getString("timelines")
        val daily = JSONObject(timelines).getJSONArray("daily")
        for (i in 0 until  daily.length()){
            var data = daily.getJSONObject(i)
            var config = JSONObject(data.getString("values"))
            Log.d("- FOR - ", "MENSAJE ${config}")
        }
    }
}