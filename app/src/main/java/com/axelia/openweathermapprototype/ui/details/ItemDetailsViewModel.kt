package com.axelia.openweathermapprototype.ui.details

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.axelia.openweathermapprototype.data.repository.WeatherListRepository
import com.axelia.openweathermapprototype.model.WeatherElement
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch


@ExperimentalCoroutinesApi
class ItemDetailsViewModel constructor(private val repository: WeatherListRepository) :
    ViewModel() {

    private lateinit var result: LiveData<WeatherElement>

    fun getItem(id: Long) : LiveData<WeatherElement> {
        result = repository.getItemById(id).asLiveData()
        return result
    }

    fun onFavoriteClicked() = viewModelScope.launch {
        if (result.value!!.isFavorite!!) {
            repository.removeAsFavoriteItem(result.value!!.id)
        } else {
            repository.favoriteItem(result.value!!.id)
        }
    }
}
