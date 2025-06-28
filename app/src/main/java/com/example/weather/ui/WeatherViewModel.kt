package com.example.weather.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.network.RetrofitInstance
import com.example.weather.data.WeatherModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {

    val isLoading = MutableLiveData<Boolean>(false)
    val error = MutableLiveData<String?>()
    val weather = MutableLiveData<WeatherModel>()


    fun fetchWeather(city: String) {
        viewModelScope.launch {
            isLoading.value = true
            error.value = null
            try {
                val result = RetrofitInstance.WeatherApi.getWeather(
                    apiKey = "62bb4ff239974c30a1895758250406",
                    location = city
                )
                weather.value = result
            } catch (e: Exception) {
                error.value = e.message ?: "Unknown Error"
            } finally {
                isLoading.value = false
            }
        }
    }


}
