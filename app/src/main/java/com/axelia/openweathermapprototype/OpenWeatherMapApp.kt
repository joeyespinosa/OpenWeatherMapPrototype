package com.axelia.openweathermapprototype

import android.app.Application
import com.axelia.openweathermapprototype.data.local.OpenWeatherMapDatabase
import com.axelia.openweathermapprototype.data.local.dao.WeatherDao
import com.axelia.openweathermapprototype.data.remote.api.MeowFactService
import com.axelia.openweathermapprototype.data.remote.api.OpenWeatherMapService
import com.axelia.openweathermapprototype.data.repository.MeowFactRepository
import com.axelia.openweathermapprototype.data.repository.MeowFactRepositoryImpl
import com.axelia.openweathermapprototype.data.repository.WeatherListRepository
import com.axelia.openweathermapprototype.ui.details.ItemDetailsViewModel
import com.axelia.openweathermapprototype.ui.main.MainActivity
import com.axelia.openweathermapprototype.ui.main.MainViewModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Provides
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.qualifier.qualifier
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@ExperimentalCoroutinesApi
class OpenWeatherMapApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidContext(applicationContext)
            modules(appModule, appModule2)
        }
    }
}

@OptIn(ExperimentalCoroutinesApi::class)
val appModule = module {

    single { Retrofit.Builder()
        .baseUrl(OpenWeatherMapService.API_URL)
        .addConverterFactory(
            MoshiConverterFactory.create(
                Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
            )
        )
        .build()
    }

    single { get<Retrofit>().create(OpenWeatherMapService::class.java) }

    single {
        OpenWeatherMapDatabase.getInstance(androidContext())
    }

    single {
        val database = get<OpenWeatherMapDatabase>()
        database.getWeatherDao()
    }

    scope<MainActivity> {  }

    viewModel {
        MainViewModel(
            repository = get(),
            meowFactRepository = get()
        )
    }

    viewModel {
        ItemDetailsViewModel(
            repository = get()
        )
    }

    single {
        WeatherListRepository(
            itemsDao = get(),
            apiService = get()
        )
    }
}

val appModule2 = module {

    single { Retrofit.Builder()
        .baseUrl(MeowFactService.API_URL)
        .addConverterFactory(
            MoshiConverterFactory.create(
                Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
            )
        )
        .build()
    }

    single { get<Retrofit>().create(MeowFactService::class.java) }

    single<MeowFactRepository> {
        MeowFactRepositoryImpl(
            service = get(),
        )
    }
}