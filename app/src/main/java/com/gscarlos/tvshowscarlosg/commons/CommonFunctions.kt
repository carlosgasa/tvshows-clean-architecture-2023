package com.gscarlos.tvshowscarlosg.commons

import android.text.Html
import android.text.Spanned
import android.text.format.DateFormat
import android.widget.ImageView
import com.bumptech.glide.Glide
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

fun ImageView.loadImage(source: String) {
    Glide.with(this).load(source).into(this)
}

fun String.toHtml(): Spanned =
    Html.fromHtml(this, Html.FROM_HTML_MODE_COMPACT)
