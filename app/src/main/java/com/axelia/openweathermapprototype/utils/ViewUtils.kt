package com.axelia.openweathermapprototype.utils

import android.view.View
import com.axelia.openweathermapprototype.R
import java.math.RoundingMode
import java.text.DecimalFormat
import kotlin.math.round
import kotlin.math.roundToInt

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}

fun Double.toTemperatureDisplay() : String{
    val df = DecimalFormat("#.#")
    df.roundingMode = RoundingMode.CEILING
    return String.format("%.1f", this)
        .plus("\u2103")
}