package com.axelia.openweathermapprototype.ui.navconcept

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.axelia.openweathermapprototype.R
import com.axelia.openweathermapprototype.databinding.FragmentMainBinding
import com.axelia.openweathermapprototype.databinding.FragmentTest1Binding
import com.axelia.openweathermapprototype.databinding.FragmentTest2Binding
import com.axelia.openweathermapprototype.model.WeatherElement
import com.axelia.openweathermapprototype.ui.details.ItemDetailsFragment
import com.axelia.openweathermapprototype.ui.main.adapter.WeatherListAdapter
import com.axelia.openweathermapprototype.ui.navconcept.NavConceptActivity
import com.axelia.openweathermapprototype.utils.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.ext.android.viewModel

@ExperimentalCoroutinesApi
class TestFragment2 : Fragment() {

    private var _binding: FragmentTest2Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTest2Binding.inflate(inflater, container, false)
        return binding.root
    }
}
