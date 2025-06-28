package com.example.weather.repository

import com.example.weather.data.UserDao
import com.example.weather.data.UserEntity


class UserRepository(private val userDao: UserDao){


    suspend fun registerUser(email:String,password:String):Boolean{
        val existingUser = userDao.getUser(email)
        return if(existingUser==null) {
            userDao.insertUser(UserEntity(email, password,isFirstLogin = true))
            true
        }else{
            false
        }
    }

    suspend fun loginUser(email: String,password: String):Boolean{
        return userDao.login(email,password)!=null
    }
    suspend fun isAnyUserRegistered(): Boolean {
        return userDao.getAnyUser() != null
    }

}