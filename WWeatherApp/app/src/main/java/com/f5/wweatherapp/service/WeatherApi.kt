package com.f5.wweatherapp.service

import com.f5.wweatherapp.model.WeatherModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("data/2.5/weather?&units=metric&appid=5bb7a8a879e2d1f70031de70e986dbbd")
    fun getData(@Query("q") cityName: String): Single<WeatherModel>

}