package com.example.weather.repository

import android.util.Log
import com.example.weather.data.UserDao
import com.example.weather.data.WeatherDao
import com.example.weather.data.WeatherEntity
import com.example.weather.data.WeatherModel
import com.example.weather.network.RetrofitInstance
import com.example.weather.network.WeatherApiService
import com.example.weather.ui.UserPreferences

class WeatherRepository(private val api:WeatherApiService,
                        private val weatherDao:WeatherDao,
                        private val userPreferences: UserPreferences,
                        private val userDao: UserDao
    ) {
    suspend fun getWeather(city: String,email:String): WeatherEntity {

        val user = userDao.getUser(email)?:throw Exception("User not found for email: $email")


        val response = api.getWeather(
            apiKey = "62bb4ff239974c30a1895758250406",
            location = city
        )
        val entity = WeatherEntity(
            city = response.location.name,
            country = response.location.country,
            temperature = response.current.temp_c,
            condition = response.current.condition.text,
            iconUrl = response.current.condition.icon,
            humidity = response.current.humidity,
            cloud = response.current.cloud,
            windSpeed = response.current.wind_kph,
            windDirection = response.current.wind_dir,
            localTime = response.location.localtime

        )

        weatherDao.insertWeather(entity)

         if(user.isFirstLogin){
             userDao.notFirstLogin(email)
         }
        return entity
    }
}
