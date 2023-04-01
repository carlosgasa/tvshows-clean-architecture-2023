package com.gscarlos.tvshowscarlosg.commons

import android.text.format.DateFormat
import java.util.*

fun trySafely(call: () -> Unit) = run {
    try {
        call()
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun Date.toShortFormat(): String {
    val c = Calendar.getInstance()
    c.timeInMillis = time
    return DateFormat.format("yyyy-MM-dd", c).toString()
}

fun Date.toLargeFormat(): String {
    val c = Calendar.getInstance()
    c.timeInMillis = time
    return DateFormat.format("EEEE d @ MMMM yyyy", c).toString().replace("@","de")
}
