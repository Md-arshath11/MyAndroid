package com.example.weather.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.weather.network.Condition

@Entity(tableName = "weather")
data class WeatherEntity(
    @PrimaryKey val id: Int = 0,
    val city: String,
    val country: String,
    val temperature: String,
    val condition: String,
    val iconUrl: String,
    val humidity: String,
    val cloud: String,
    val windSpeed: String,
    val windDirection: String,
    val localTime: String
)

