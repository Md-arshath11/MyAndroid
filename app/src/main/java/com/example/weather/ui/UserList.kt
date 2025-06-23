package com.example.weather.ui

import com.example.weather.data.User

object UserList{
    val users = mutableListOf<User>()

    fun isEmailRegistered(email: String):Boolean{
        return users.any(){it.email == email}
    }

    fun register(email:String,password:String): Boolean {
        if (isEmailRegistered(email)){
            return false
        } else{
            users.add(User(email,password))
            return true
        }
    }

    fun login(email: String,password: String): Boolean{
        return users.any{it.email==email && it.password==password }
    }
}