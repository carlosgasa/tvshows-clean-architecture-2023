package com.gscarlos.tvshowscarlosg.data.local.database

import android.content.Context
import androidx.room.Room

object AppDatabaseBuilder {

    private const val DATABASE_NAME = "database-tv-shows"

    fun getInstance(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context, AppDatabase::class.java, DATABASE_NAME
        )
            .allowMainThreadQueries()
            .build()
    }
}