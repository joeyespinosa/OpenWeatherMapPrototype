package com.axelia.openweathermapprototype.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.axelia.openweathermapprototype.model.WeatherElement
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertItems(items: List<WeatherElement>)

    @Query("DELETE FROM ${WeatherElement.TABLE_NAME}")
    fun deleteAllItems()

    @Query("SELECT * FROM ${WeatherElement.TABLE_NAME} WHERE id = :weatherId")
    fun getItemById(weatherId: Long): Flow<WeatherElement>

    @Query("SELECT * FROM ${WeatherElement.TABLE_NAME}")
    fun getAllItems(): Flow<List<WeatherElement>>

    @Query("UPDATE ${WeatherElement.TABLE_NAME} SET isFavorite = 1 WHERE id = :weatherId")
    suspend fun favoriteItem(weatherId: Long): Int

    @Query("UPDATE ${WeatherElement.TABLE_NAME} SET isFavorite = 0 WHERE id = :weatherId")
    suspend fun removeAsFavoriteItem(weatherId: Long): Int
}