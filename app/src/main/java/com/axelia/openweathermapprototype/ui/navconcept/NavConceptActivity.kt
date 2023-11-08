package com.axelia.openweathermapprototype.ui.navconcept

import android.app.Activity
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.NavHostFragment
import com.afollestad.materialdialogs.MaterialDialog
import com.axelia.openweathermapprototype.R
import com.axelia.openweathermapprototype.databinding.ActivityMainBackupBinding
import com.axelia.openweathermapprototype.databinding.ActivityMainBinding
import com.axelia.openweathermapprototype.ui.base.BaseActivity
import com.axelia.openweathermapprototype.utils.replaceFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.ext.android.viewModel

@ExperimentalCoroutinesApi
class NavConceptActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBackupBinding.inflate(layoutInflater)
        val navHostFragment =
            (supportFragmentManager.findFragmentById(binding.fragmentContainer.id) as NavHostFragment)
        val navController = navHostFragment.navController

        setContentView(binding.root)

        navController.setGraph(
            R.navigation.nav_openweathermap,
        )
    }
}
