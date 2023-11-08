package com.axelia.openweathermapprototype.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.axelia.openweathermapprototype.data.repository.WeatherListRepository
import com.axelia.openweathermapprototype.model.WeatherElement
import com.axelia.openweathermapprototype.utils.State
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import retrofit2.http.Query

/**
 * ViewModel for [MainActivity]
 */
@ExperimentalCoroutinesApi
class MainViewModel constructor(private val repository: WeatherListRepository) :
    ViewModel() {

    private val _itemLiveData = MutableLiveData<State<List<WeatherElement>>>()

    val itemLiveData: LiveData<State<List<WeatherElement>>>
        get() = _itemLiveData

    fun getItems(
        cityIds: String,
        appId: String?,
        units: String?
    ) {
        viewModelScope.launch {
            repository.getAllItems(
                cityIds, appId, units
            ).collect {
                _itemLiveData.value = it
            }
        }
    }
}
