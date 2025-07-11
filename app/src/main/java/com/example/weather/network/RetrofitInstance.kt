package com.example.weather.network

import com.example.weather.data.WeatherModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

object RetrofitInstance {
    private const val BASE_URL = "https://api.weatherapi.com"

    val WeatherApi:WeatherApiService by lazy {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(WeatherApiService::class.java)
    }

}
interface WeatherApiService{
    @GET("v1/current.json")
    suspend fun getWeather(
        @Query("key") apiKey: String,
        @Query("q") location: String,
        @Query("aqi") aqi: String = "no"
    ): WeatherModel
}
