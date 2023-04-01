package com.gscarlos.tvshowscarlosg.data.remote

import android.content.Context
import com.gscarlos.tvshowscarlosg.R

sealed class DataResult<out T, out E> {
    data class Success<out T>(val data: T) : DataResult<T, Nothing>()
    data class Error<out E>(val errorType: DataResultError) : DataResult<Nothing, E>()
    object Loading : DataResult<Nothing, Nothing>()
}

sealed class DataResultError {
    object ResultEmpty : DataResultError()
    object NoInternetError : DataResultError()
    object ServiceError : DataResultError()

    fun getMessage(context: Context) =
        when (this) {
            NoInternetError -> context.getString(R.string.error_internet)
            ResultEmpty -> context.getString(R.string.error_empty)
            ServiceError -> context.getString(R.string.error_service)
        }
}