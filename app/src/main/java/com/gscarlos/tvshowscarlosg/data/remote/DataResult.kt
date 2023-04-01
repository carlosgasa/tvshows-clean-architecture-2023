package com.gscarlos.tvshowscarlosg.data.remote

import android.content.Context
import com.gscarlos.tvshowscarlosg.R

sealed class DataResult<out T, out E> {
    data class Success<out T>(val data: T) : DataResult<T, Nothing>()
    data class Error<out E>(val errorType: DataResultError) : DataResult<Nothing, E>()
    object Loading : DataResult<Nothing, Nothing>()
}

sealed class DataResultError {
    object NoError : DataResultError()
    object ResultEmpty : DataResultError()
    object NoInternetError : DataResultError()
    object NoInternetErrorSearch : DataResultError()
    object ServiceError : DataResultError()

    fun getMessage(context: Context) =
        when (this) {
            NoInternetError -> context.getString(R.string.error_internet)
            NoInternetErrorSearch -> context.getString(R.string.error_internet)
            ResultEmpty -> context.getString(R.string.error_empty)
            ServiceError -> context.getString(R.string.error_service)
            NoError -> ""
        }

    fun getAction(context: Context) =
        when (this) {
            NoInternetError -> context.getString(R.string.txt_retry)
            NoInternetErrorSearch -> null
            ResultEmpty -> null
            ServiceError -> null
            NoError -> null
        }

    fun getImage() =
        when (this) {
            NoInternetError -> R.drawable.undraw_signal_searching_re_yl8n
            NoInternetErrorSearch -> R.drawable.undraw_signal_searching_re_yl8n
            ResultEmpty -> R.drawable.undraw_no_data_re_kwbl
            ServiceError -> R.drawable.undraw_signal_searching_re_yl8n
            NoError -> 0
        }
}