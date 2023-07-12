package com.f5.retrofitpokeapi.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitClient {
    val URI = "https://pokeapi.co/api/v2/pokemon/"
    private val retrofit = Retrofit.Builder()
        .baseUrl(URI)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val consumidorApi = retrofit.create(ConsumerAPI::class.java)
}