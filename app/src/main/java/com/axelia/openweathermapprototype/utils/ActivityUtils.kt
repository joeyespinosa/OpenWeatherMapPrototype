package com.axelia.openweathermapprototype.utils

import android.app.Activity
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * Can show [Toast] from every [Activity].
 */
fun Activity.showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(applicationContext, message, duration).show()
}

/**
 * Returns Color from resource.
 * @param id Color Resource ID
 */
fun Activity.getColorRes(@ColorRes id: Int) = ContextCompat.getColor(applicationContext, id)

/**
 * Provides [ViewModel] of type [VM] from [factory].
 */
inline fun <reified VM : ViewModel> AppCompatActivity.viewModelOf(factory: ViewModelProvider.Factory) =
    ViewModelProvider(this, factory).get(VM::class.java)


fun Activity.replaceFragment(
    fragmentManager: FragmentManager,
    fragment: Fragment, frameId: Int
) {
    val transaction = fragmentManager.beginTransaction()
    transaction.replace(frameId, fragment)
    transaction.commit()
}

fun Activity.addFragment(
    fragmentManager: FragmentManager,
    fragment: Fragment, frameId: Int
) {
    val transaction = fragmentManager.beginTransaction()
    transaction
        .add(frameId, fragment)
        .addToBackStack(null)
    transaction.commit()
}