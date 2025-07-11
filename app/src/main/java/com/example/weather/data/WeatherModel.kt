package com.example.weather.data

import com.example.weather.network.Current
import com.example.weather.network.Location

data class WeatherModel(
    val current: Current,
    val location: Location
)