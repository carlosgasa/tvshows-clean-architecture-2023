package com.gscarlos.tvshowscarlosg.commons

import android.content.Context
import android.content.res.Configuration
import android.text.Html
import android.text.Spanned
import android.text.format.DateFormat
import android.widget.ImageView
import com.bumptech.glide.Glide
import java.util.*

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

fun Context.isTablet(): Boolean {
    return ((resources.configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE)
}
