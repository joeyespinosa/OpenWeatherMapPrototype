package com.axelia.openweathermapprototype.ui.details

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.TypedValue
import android.view.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.axelia.openweathermapprototype.R
import com.axelia.openweathermapprototype.databinding.FragmentWeatherDetailsBinding
import com.axelia.openweathermapprototype.model.WeatherElement
import com.axelia.openweathermapprototype.utils.toTemperatureDisplay
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.koin.androidx.viewmodel.ext.android.viewModel

@ExperimentalCoroutinesApi
class ItemDetailsFragment : Fragment() {

    private val mViewModel: ItemDetailsViewModel by viewModel()
    private lateinit var itemDetail: WeatherElement

    private var _binding: FragmentWeatherDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWeatherDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val itemId = arguments?.getLong(WEATHER_ID)
        initItem(itemId!!)

        requireActivity().actionBar?.setDisplayShowHomeEnabled(true)
        requireActivity().actionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun initItem(itemId: Long) {
        mViewModel.getItem(itemId).observe(viewLifecycleOwner, Observer { item ->
            binding.apply {
                this@ItemDetailsFragment.itemDetail = item
                textviewDetailCity.text = item.name
                textviewDetailCurrentTemp.text = item.main.temp.toTemperatureDisplay()
                textviewDetailStatus.text = item.weather[0].main
                textviewDetailsTempMinMax.text =
                    item.main.tempMin.toTemperatureDisplay()
                        .plus("/")
                        .plus(item.main.tempMax.toTemperatureDisplay())
                imageviewFavorite.background = getFavoriteIcon(item.isFavorite!!)
            }
        })

        binding.imageviewFavorite.setOnClickListener {
            runBlocking {
                mViewModel.onFavoriteClicked()
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.detail_menu, menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                activity?.supportFinishAfterTransition()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val WEATHER_ID = "weatherId"
    }
}
