package com.f5.retrofitpokeapi.service


import retrofit2.http.GET
import com.f5.retrofitpokeapi.models.PokemonModel
import retrofit2.Call

interface ConsumerAPI {
    @GET("pikachu")
    fun getPokemon(): Call<PokemonModel>
}