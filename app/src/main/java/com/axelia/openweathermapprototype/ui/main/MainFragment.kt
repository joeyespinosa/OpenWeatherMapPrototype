package com.axelia.openweathermapprototype.ui.main

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.axelia.openweathermapprototype.R
import com.axelia.openweathermapprototype.databinding.FragmentMainBinding
import com.axelia.openweathermapprototype.model.WeatherElement
import com.axelia.openweathermapprototype.ui.details.ItemDetailsFragment
import com.axelia.openweathermapprototype.ui.main.adapter.WeatherListAdapter
import com.axelia.openweathermapprototype.utils.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainFragment : Fragment(),
    WeatherListAdapter.OnItemClickListener {

    private val mViewModel: MainViewModel by viewModels()
    private val mAdapter: WeatherListAdapter by lazy { WeatherListAdapter(onItemClickListener = this) }

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initItems()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initItems() {
        binding.recyclerviewWeather.apply {
            layoutManager = LinearLayoutManager(this@MainFragment.activity)
            adapter = mAdapter
        }

        handleNetworkChanges()

        mViewModel.itemLiveData.observe(viewLifecycleOwner, Observer { state ->
            when (state) {
                is State.Loading -> showLoading(true)
                is State.Success -> {
                    if (state.data.isNotEmpty()) {
                        mAdapter.submitList(state.data.toMutableList())
                        showLoading(false)
                    }
                }
                is State.Error -> {
                    requireActivity().showToast(state.message)
                    showLoading(false)
                }
            }
        })

        binding.swiperefreshlayout.setOnRefreshListener {
            getWeatherItems()
        }
    }

    private fun itemsString(items: List<String>) = items.joinToString(separator = ",")

    private fun getWeatherItems() {
        val items = listOf(
            "1701668", "3067696", "1835848"
        )

        mViewModel.getItems(
            itemsString(items),
            "30f986aba0898a41fd691f43a033eadf",
            "metric"
        )
    }

    private fun showLoading(isLoading: Boolean) {
        binding!!.swiperefreshlayout.isRefreshing = isLoading
    }

    private fun handleNetworkChanges() {
        NetworkUtils.getNetworkLiveData(requireActivity().applicationContext)
            .observe(viewLifecycleOwner, Observer { isConnected ->
                if (!isConnected) {
                    binding.textviewNetworkStatus.text = getString(R.string.text_no_connectivity)
                    binding.linearlayoutNetworkStatus.apply {
                        show()
                        setBackgroundColor(requireActivity().getColorRes(R.color.colorStatusNotConnected))
                    }
                } else {
                    if (mViewModel.itemLiveData.value is State.Error || mAdapter.itemCount == 0) {
                        getWeatherItems()
                    }

                    binding.textviewNetworkStatus.text = getString(R.string.text_connectivity)
                    binding.linearlayoutNetworkStatus.apply {
                        setBackgroundColor(requireActivity().getColorRes(R.color.colorStatusConnected))
                        animate()
                            .alpha(1f)
                            .setStartDelay(ANIMATION_DURATION)
                            .setDuration(ANIMATION_DURATION)
                            .setListener(object : AnimatorListenerAdapter() {
                                override fun onAnimationEnd(animation: Animator) {
                                    hide()
                                }
                            })
                    }
                }
            })
    }

    companion object {
        const val ANIMATION_DURATION = 1000.toLong()
    }

    override fun onItemClicked(item: WeatherElement) {

        val bundle = Bundle()
        bundle.putLong(ItemDetailsFragment.WEATHER_ID, item.id)

        val fragment = ItemDetailsFragment()
        fragment.arguments = bundle

        activity?.addFragment(
            activity?.supportFragmentManager!!, fragment, R.id.fragment_container
        )
    }
}
