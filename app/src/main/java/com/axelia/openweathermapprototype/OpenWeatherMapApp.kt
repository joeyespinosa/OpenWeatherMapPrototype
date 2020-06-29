package com.axelia.openweathermapprototype

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@HiltAndroidApp
class OpenWeatherMapApp : Application() {

    override fun onCreate() {
        super.onCreate()

    }
}