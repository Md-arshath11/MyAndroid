package com.example.weather.ui

import com.example.weather.data.User

class UserList{
    private val users = mutableListOf<User>()

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

    fun getUsers():List<User> = users
}