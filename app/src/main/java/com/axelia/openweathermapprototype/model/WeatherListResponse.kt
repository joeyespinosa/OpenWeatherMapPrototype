package com.axelia.openweathermapprototype.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.axelia.openweathermapprototype.model.WeatherElement.Companion.TABLE_NAME
import com.squareup.moshi.Json

data class WeatherListResponse (
    val cnt: Long,

    @Json(name = "list")
    val weatherElementList: List<WeatherElement>
)

@Entity(tableName = TABLE_NAME)
data class WeatherElement (

    @PrimaryKey
    val id: Long,
    val coord: Coord?,
    val sys: Sys?,
    val weather: List<Weather>,
    val main: Main,
    val visibility: Long?,
    val wind: Wind?,
    val clouds: Clouds?,
    val dt: Long?,

    val name: String,

    @Json(name = "is_favorite")
    var isFavorite: Boolean? = false
) {
    companion object {
        const val TABLE_NAME = "weather_element"
    }
}

data class Clouds (
    val all: Long
)

data class Coord (
    val lon: Double,
    val lat: Double
)

data class Main (
    val temp: Double,

    @Json(name = "feels_like")
    val feelsLike: Double,

    @Json(name = "temp_min")
    val tempMin: Double,

    @Json(name = "temp_max")
    val tempMax: Double,
    val pressure: Long,
    val humidity: Long
)

data class Sys (
    val country: String?,
    val timezone: Long?,
    val sunrise: Long?,
    val sunset: Long?
)

data class Weather (
    val id: Long?,
    val main: String?,
    val description: String?,
    val icon: String?
)

data class Wind (
    val speed: Double?,
    val deg: Long?
)
