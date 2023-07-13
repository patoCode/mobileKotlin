package com.f5.doglist.service

import com.f5.doglist.models.DogResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface APIService{

    @GET
    suspend fun getDogsByRace(@Url url: String): Response<DogResponse>

}