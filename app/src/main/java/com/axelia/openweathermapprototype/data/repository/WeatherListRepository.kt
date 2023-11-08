package com.axelia.openweathermapprototype.data.repository

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.MainThread
import com.axelia.openweathermapprototype.data.local.dao.WeatherDao
import com.axelia.openweathermapprototype.data.remote.api.OpenWeatherMapService
import com.axelia.openweathermapprototype.model.WeatherElement
import com.axelia.openweathermapprototype.model.WeatherListResponse
import com.axelia.openweathermapprototype.utils.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import retrofit2.http.Query
import javax.inject.Inject
import javax.inject.Singleton


@ExperimentalCoroutinesApi
class WeatherListRepository constructor(
    private val itemsDao: WeatherDao,
    private val apiService: OpenWeatherMapService
) : Parcelable {

    constructor(parcel: Parcel) : this(
        TODO("itemsDao"),
        TODO("apiService")
    ) {
    }

    fun getAllItems(
        cityIds: String,
        appId: String?,
        units: String?
    ): Flow<State<List<WeatherElement>>> {
        return object : NetworkBoundRepository<List<WeatherElement>, WeatherListResponse>() {

            override suspend fun saveRemoteData(response: WeatherListResponse) = itemsDao.insertItems(response.weatherElementList)

            override fun fetchFromLocal(): Flow<List<WeatherElement>> = itemsDao.getAllItems()

            override suspend fun fetchFromRemote(): Response<WeatherListResponse> = apiService.getWeatherList(
                cityIds = cityIds,
                appId = appId,
                units = units
            )

        }.asFlow().flowOn(Dispatchers.IO)
    }

    @MainThread
    fun getItemById(itemId: Long): Flow<WeatherElement> = itemsDao.getItemById(itemId)

    suspend fun favoriteItem(itemId: Long) = itemsDao.favoriteItem(itemId)

    suspend fun removeAsFavoriteItem(itemId: Long) = itemsDao.removeAsFavoriteItem(itemId)

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<WeatherListRepository> {
        override fun createFromParcel(parcel: Parcel): WeatherListRepository {
            return WeatherListRepository(parcel)
        }

        override fun newArray(size: Int): Array<WeatherListRepository?> {
            return arrayOfNulls(size)
        }
    }
}
