package com.example.weather.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.repository.UserRepository
import com.example.weather.data.WeatherEntity
import com.example.weather.repository.WeatherRepository
import kotlinx.coroutines.launch

class WeatherViewModel (private val repository: WeatherRepository,
                         private val userRepository: UserRepository,

                       ): ViewModel() {



    val isLoading = MutableLiveData<Boolean>(false)
    val error = MutableLiveData<String?>()
    val weather = MutableLiveData<WeatherEntity>()

    var loggedInEmail: String? = null

    val loginSuccess = MutableLiveData<Boolean>()
    val registerSuccess = MutableLiveData<Boolean>()

    fun fetchWeather(city: String,email: String) {
        viewModelScope.launch {
            isLoading.value = true
            error.value = null
            try {
                val result = repository.getWeather(city,email)
                Log.d("WeatherFetch", "Fetched Weather: ${result.city}")
                weather.value = result
            } catch (e: Exception) {
                Log.e("WeatherViewModel", "Error fetching weather", e)
                error.value = e.message ?: "Unknown Error"
            } finally {
                isLoading.value = false
            }
        }
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            val isSuccess = userRepository.loginUser(email, password)
            loginSuccess.value = isSuccess
            if (isSuccess) {
                loggedInEmail = email
            }
        }
    }


    fun register(email:String,password: String){
        viewModelScope.launch {
            registerSuccess.value=userRepository.registerUser(email, password)
        }
    }

}
