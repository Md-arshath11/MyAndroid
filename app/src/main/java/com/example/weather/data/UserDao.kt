package com.example.weather.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao{
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(user:UserEntity)

    @Query("SELECT * FROM users WHERE email = :email")
    suspend fun getUser(email:String):UserEntity?

    @Query("SELECT * FROM users WHERE email = :email AND password = :password")
    suspend fun login(email:String,password:String):UserEntity?

    @Query("SELECT * FROM users LIMIT 1")
    suspend fun getAnyUser(): UserEntity?

    @Query("UPDATE users SET isFirstLogin = 0 WHERE email = :email")
    suspend fun notFirstLogin(email: String)


}