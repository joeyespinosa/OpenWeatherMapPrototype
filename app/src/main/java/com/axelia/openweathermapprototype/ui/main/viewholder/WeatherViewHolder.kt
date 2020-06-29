package com.axelia.openweathermapprototype.ui.main.viewholder

import android.graphics.Color
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.axelia.openweathermapprototype.R
import com.axelia.openweathermapprototype.databinding.ItemWeatherBinding
import com.axelia.openweathermapprototype.model.WeatherElement
import com.axelia.openweathermapprototype.ui.main.adapter.WeatherListAdapter
import com.axelia.openweathermapprototype.utils.toTemperatureDisplay

class WeatherViewHolder(private val binding: ItemWeatherBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: WeatherElement, onItemClickListener: WeatherListAdapter.OnItemClickListener? = null) {
        binding.textviewListCity.text = item.name
        binding.textviewListTemp.text = item.main.temp.toTemperatureDisplay()
        binding.textviewListStatus.text = item.weather[0].main

        binding.constraintlayoutListItem.setBackgroundColor(getBackgroundColor(item))
        binding.imageviewFavoriteItem.background = getFavoriteIcon(item.isFavorite!!)

        onItemClickListener?.let { listener ->
            binding.root.setOnClickListener {
                listener.onItemClicked(item)
            }
        }
    }

    private fun getFavoriteIcon(isFavorite : Boolean) : Drawable? {
        return if (isFavorite) {
            ContextCompat.getDrawable(binding.root.context, R.drawable.ic_favorite_black_24dp)
        } else {
            ContextCompat.getDrawable(binding.root.context, R.drawable.ic_favorite_border_black_24dp)
        }
    }

    private fun getBackgroundColor(item : WeatherElement) : Int {
        return when(item.main.temp) {
            in Double.MIN_VALUE..0.0 -> {
                Color.parseColor("#1976D2")
            }
            in 0.0..15.0 -> {
                Color.parseColor("#26C6DA")
            }
            in 15.1..30.0 -> {
                Color.parseColor("#66BB6A")
            }
            else -> {
                Color.parseColor("#FF7043")
            }
        }
    }
}
