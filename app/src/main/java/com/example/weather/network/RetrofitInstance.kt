package com.example.weather.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "https://api.weatherapi.com"

    private fun getInstance(): Retrofit {

       return Retrofit.Builder()
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .baseUrl(BASE_URL)
            .build()
    }
    val weatherApi : WeatherApi= getInstance().create(WeatherApi::class.java)
}
