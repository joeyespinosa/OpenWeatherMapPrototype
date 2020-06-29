package com.axelia.openweathermapprototype.data.remote.api

import com.axelia.openweathermapprototype.model.WeatherListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherMapService {

    @GET("data/2.5/group")
    suspend fun getWeatherList(
        @Query("id", encoded = true) cityIds: String,
        @Query("appid") appId: String?,
        @Query("units") units: String?
    ): Response<WeatherListResponse>

    companion object {
        const val API_URL = "https://api.openweathermap.org/"
    }
}