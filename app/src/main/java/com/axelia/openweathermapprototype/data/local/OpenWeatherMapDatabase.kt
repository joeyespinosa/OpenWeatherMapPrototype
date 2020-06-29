package com.axelia.openweathermapprototype.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.axelia.openweathermapprototype.data.local.dao.WeatherDao
import com.axelia.openweathermapprototype.model.UtilConverters
import com.axelia.openweathermapprototype.model.WeatherElement

@Database(
    entities = [WeatherElement::class],
    version = DatabaseMigrations.DB_VERSION
)
@TypeConverters(UtilConverters::class)
abstract class OpenWeatherMapDatabase : RoomDatabase() {

    abstract fun getWeatherDao(): WeatherDao

    companion object {
        const val DB_NAME = "openweathermap_database"

        @Volatile
        private var INSTANCE: OpenWeatherMapDatabase? = null

        fun getInstance(context: Context): OpenWeatherMapDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        OpenWeatherMapDatabase::class.java,
                        DB_NAME
                    )
                    .addMigrations(*DatabaseMigrations.MIGRATIONS)
                    .build()

                INSTANCE = instance
                return instance
            }
        }

    }
}