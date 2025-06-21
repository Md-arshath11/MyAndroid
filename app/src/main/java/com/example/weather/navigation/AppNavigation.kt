package com.example.register.ui

import android.content.Context
import android.os.UserManager
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.*
import com.example.weather.ui.WeatherApp
import com.example.weather.ui.WeatherViewModel


@Composable
fun AppNavigation(weatherViewModel: WeatherViewModel) {
    val navController = rememberNavController()
    val context= LocalContext.current
    val userPrefs = remember { UserPreferences(context) }
    var startDestination by remember { mutableStateOf("register") }

    LaunchedEffect(Unit) {
        val isRegistered = userPrefs.isUserRegistered()
        startDestination = if(isRegistered)"login" else "register"
    }

    NavHost(navController, startDestination = startDestination) {
        composable("register") { RegisterScreen(navController) }
        composable("login") { LoginScreen(navController) }
        composable("home") { WeatherApp(viewModel = weatherViewModel,navController= rememberNavController()) }
    }
}