package com.f5.weather_material.domains

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import org.json.JSONObject
import java.time.LocalDate
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Date


object utils {

    @RequiresApi(Build.VERSION_CODES.O)
    fun jsonToListForWeek(data: String): List<Hourly>{
        val timelines = JSONObject(data).getString("timelines")
        val daily = JSONObject(timelines).getJSONArray("daily")
        val list = mutableListOf<Hourly>()
        for (i in 0 until  daily.length()){
            var data = daily.getJSONObject(i)
            var daily = JSONObject(data.getString("values"))
            val dateTime = ZonedDateTime.parse(data.getString("time"))
            val localDateTime = dateTime.toLocalDateTime().format(DateTimeFormatter.ISO_LOCAL_DATE)
           // Log.d("-DAILY-", "TIME IS: ${localDateTime}")
            list.add(Hourly(data.getString("time"), daily.getInt("temperatureAvg"), "sun"))
        }
        return list
    }
}