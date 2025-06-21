
package com.example.weather.ui

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.weather.ui.UserPreferences
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(navController: NavController) {
    val context = LocalContext.current
    val userPrefs = remember { UserPreferences(context) }
    val scope = rememberCoroutineScope()

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Login")
        Spacer(Modifier.height(20.dp))

        OutlinedTextField(value = email,
            onValueChange = { email = it },
            label = { Text("Email") })
        Spacer(Modifier.height(10.dp))
        OutlinedTextField(value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(Modifier.height(20.dp))

        Button(onClick = {
            scope.launch {
                val valid = userPrefs.isUserValid(email, password)
                if (valid) {
                    Toast.makeText(context, "Login Successful", Toast.LENGTH_SHORT).show()
                    navController.navigate("home")
                } else {
                    Toast.makeText(context, "Invalid Credentials , Click Register to your account", Toast.LENGTH_SHORT).show()
                }
            }
        }) {
            Text("Login")
        }
        Button(onClick = { navController.navigate("register")}
        ){
            Text("Register")
        }
    }

}
