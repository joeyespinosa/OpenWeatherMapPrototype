package com.axelia.openweathermapprototype.data.repository

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.MainThread
import com.axelia.openweathermapprototype.data.local.dao.WeatherDao
import com.axelia.openweathermapprototype.data.remote.api.MeowFactService
import com.axelia.openweathermapprototype.data.remote.api.OpenWeatherMapService
import com.axelia.openweathermapprototype.model.WeatherElement
import com.axelia.openweathermapprototype.model.WeatherListResponse
import com.axelia.openweathermapprototype.utils.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import retrofit2.http.Query
import javax.inject.Inject
import javax.inject.Singleton


interface MeowFactRepository {
    suspend fun getMeowFacts() : Flow<List<String>>
}

class MeowFactRepositoryImpl(
    private val service: MeowFactService
) : MeowFactRepository {
    override suspend fun getMeowFacts(): Flow<List<String>> {
        return flow {
            emit(
                service.getMeowFacts().body()?.data!!
            )
        }
    }
}