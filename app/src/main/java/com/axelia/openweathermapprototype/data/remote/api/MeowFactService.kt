package com.axelia.openweathermapprototype.data.remote.api

import com.axelia.openweathermapprototype.model.WeatherListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MeowFactService {

    @GET("?count=3")
    suspend fun getMeowFacts(
    ): Response<MeowFactResponse>

    companion object {
        const val API_URL = "https://meowfacts.herokuapp.com/"
    }
}

data class MeowFactResponse(
    val data: List<String>
)