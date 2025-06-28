package com.example.weather

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.example.weather.data.WeatherDatabase
import com.example.weather.navigation.AppNavigation
import com.example.weather.network.RetrofitInstance
import com.example.weather.repository.UserRepository
import com.example.weather.repository.WeatherRepository
import com.example.weather.ui.UserPreferences

import com.example.weather.ui.WeatherViewModel
import com.example.weather.ui.theme.WeatherTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val dao = WeatherDatabase.getWeatherData(applicationContext).weatherDao()
        val api = RetrofitInstance.WeatherApi
        val userPrefs = UserPreferences(applicationContext)

        val userDao = WeatherDatabase.getWeatherData(applicationContext).userDao()
        val userRepository= UserRepository(userDao)
        val repository = WeatherRepository(api,dao,userPrefs,userDao )

       val weatherViewModel = WeatherViewModel(repository,userRepository)
        setContent {
            WeatherTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        AppNavigation(weatherViewModel,userRepository)
                    }
            }
        }
    }
}

