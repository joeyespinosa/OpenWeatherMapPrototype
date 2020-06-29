package com.axelia.openweathermapprototype.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.axelia.openweathermapprototype.databinding.ItemWeatherBinding
import com.axelia.openweathermapprototype.model.WeatherElement
import com.axelia.openweathermapprototype.ui.main.viewholder.WeatherViewHolder

class WeatherListAdapter(private val onItemClickListener: OnItemClickListener) :
    ListAdapter<WeatherElement, WeatherViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = WeatherViewHolder(
        ItemWeatherBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) =
        holder.bind(getItem(position), onItemClickListener)

    interface OnItemClickListener {
        fun onItemClicked(item: WeatherElement)
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<WeatherElement>() {
            override fun areItemsTheSame(oldItem: WeatherElement, newItem: WeatherElement): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: WeatherElement, newItem: WeatherElement): Boolean =
                oldItem == newItem

        }
    }
}
