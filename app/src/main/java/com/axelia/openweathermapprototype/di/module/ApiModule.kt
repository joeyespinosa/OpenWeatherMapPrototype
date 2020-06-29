package com.axelia.openweathermapprototype.di.module

import com.axelia.openweathermapprototype.data.remote.api.OpenWeatherMapService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
class ApiModule {

    @Singleton
    @Provides
    fun provideRetrofitService(): OpenWeatherMapService = Retrofit.Builder()
        .baseUrl(OpenWeatherMapService.API_URL)
        .addConverterFactory(
            MoshiConverterFactory.create(
                Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
            )
        )
        .build()
        .create(OpenWeatherMapService::class.java)

}
