package com.gscarlos.tvshowscarlosg.commons

fun trySafely(call: () -> Unit) = run {
    try {
        call()
    } catch (e: Exception) {
        e.printStackTrace()
    }
}