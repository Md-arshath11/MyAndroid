
package com.example.weather.ui

import android.content.Context
import android.util.Log
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

private val Context.datastore by preferencesDataStore("user_prefs")

class UserPreferences(private val context: Context){
    companion object{
        val EMAIL = stringPreferencesKey("email")
        val PASSWORD = stringPreferencesKey("password")
        val IS_FIRST_LOGIN = booleanPreferencesKey("is_first_login")
    }

    suspend fun saveUser(email:String,password:String){
        context.datastore.edit {
            it[EMAIL]= email
            it[PASSWORD]=password
            it[IS_FIRST_LOGIN] = true
        }
    }
    suspend fun isUserValid(email: String,password: String):Boolean{
        val savedEmail = context.datastore.data.map {it[EMAIL]?:""}.first()
        val savedPassword = context.datastore.data.map { it[PASSWORD]?:""}.first()
        return email == savedEmail && password == savedPassword
    }
    suspend fun isEmailRegistered(email: String): Boolean {
        val savedEmail = context.datastore.data
            .map { it[EMAIL] ?: "" }
            .first()
        return savedEmail == email
    }


    suspend fun isUserRegistered(): Boolean {
        val email = context.datastore.data.map { it[EMAIL] ?: "" }.first()
        val password = context.datastore.data.map { it[PASSWORD] ?: "" }.first()
        return email.isNotEmpty() && password.isNotEmpty()
    }
    suspend fun isFirstLogin():Boolean{
        val isFirst = context.datastore.data.map {
            it[IS_FIRST_LOGIN]?:true
        }.first()
        return isFirst
    }

    suspend fun saveIsFirstLogin(value:Boolean){
        context.datastore.edit {
            it[IS_FIRST_LOGIN]=value
        }
    }

}
