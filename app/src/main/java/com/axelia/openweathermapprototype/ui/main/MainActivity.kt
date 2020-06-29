package com.axelia.openweathermapprototype.ui.main

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.fragment.app.FragmentManager
import com.afollestad.materialdialogs.MaterialDialog
import com.axelia.openweathermapprototype.R
import com.axelia.openweathermapprototype.databinding.ActivityMainBinding
import com.axelia.openweathermapprototype.ui.base.BaseActivity
import com.axelia.openweathermapprototype.utils.replaceFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {

    override val mViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)

        super.onCreate(savedInstanceState)
        setContentView(mViewBinding.root)

        val fragment = MainFragment()

        this.replaceFragment(
            supportFragmentManager, fragment, R.id.fragment_container
        )

        setSupportActionBar(mViewBinding.toolbar)
        supportFragmentManager.addOnBackStackChangedListener {
            shouldDisplayHomeUp();
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                supportFragmentManager.popBackStack();
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun shouldDisplayHomeUp() {
        //Enable Up button only  if there are entries in the back stack
        val canGoBack = supportFragmentManager.backStackEntryCount > 0
        supportActionBar!!.setDisplayHomeAsUpEnabled(canGoBack)
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack();
        } else {
            MaterialDialog(this).show {
                title(text = getString(R.string.exit_dialog_title))
                message(text = getString(R.string.exit_dialog_message))
                positiveButton(text = getString(R.string.option_yes)) { dialogInterface ->
                    dialogInterface.dismiss()
                    super.onBackPressed()
                }
                negativeButton(text = getString(R.string.option_no)) { dialogInterface ->
                    dialogInterface.dismiss()
                }
            }
        }
    }

    override fun getViewBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
}
