package com.axelia.openweathermapprototype.di.module

import android.app.Application
import com.axelia.openweathermapprototype.data.local.OpenWeatherMapDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(application: Application) = OpenWeatherMapDatabase.getInstance(application)

    @Singleton
    @Provides
    fun provideWeatherDao(database: OpenWeatherMapDatabase) = database.getWeatherDao()
}