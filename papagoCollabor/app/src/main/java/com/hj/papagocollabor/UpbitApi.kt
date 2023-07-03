package com.hj.papagocollabor

import retrofit2.Call
import retrofit2.http.GET

interface UpbitAPI {
    @GET("v1/market/all")
    fun getCoinAll(
    ): Call<List<Coin>>
}