package com.example.weather.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.*
import com.example.weather.ui.LoginScreen
import com.example.weather.ui.RegisterScreen
import com.example.weather.ui.UserList
import com.example.weather.ui.UserPreferences
import com.example.weather.ui.WeatherApp
import com.example.weather.ui.WeatherViewModel


@Composable
fun AppNavigation(weatherViewModel: WeatherViewModel) {
    val navController = rememberNavController()
    val userList = remember { UserList()}
    val context= LocalContext.current
    val userPrefs = remember { UserPreferences(context) }
    var startDestination by remember { mutableStateOf("register") }

    LaunchedEffect(Unit) {
        val isRegistered = userPrefs.isUserRegistered()
        startDestination = if(isRegistered)"login" else "register"
    }

    NavHost(navController, startDestination = startDestination) {
        composable("register") { RegisterScreen(navController, userList) }
        composable("login") { LoginScreen(navController, userList) }
        composable("home") { WeatherApp(viewModel = weatherViewModel,navController= rememberNavController()) }
    }
}