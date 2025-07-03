package com.example.weather.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [WeatherEntity::class, UserEntity::class], version = 7)
abstract class WeatherDatabase : RoomDatabase(){
    abstract fun weatherDao():WeatherDao
    abstract fun userDao():UserDao

    companion object{
        private var INSTANCE:WeatherDatabase?= null
        fun getWeatherData(context: Context):WeatherDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                                context.applicationContext,
                                WeatherDatabase::class.java,
                                "weather_database"
                            ).fallbackToDestructiveMigration(true)
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}