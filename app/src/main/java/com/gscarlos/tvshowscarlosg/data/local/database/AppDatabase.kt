package com.gscarlos.tvshowscarlosg.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gscarlos.tvshowscarlosg.data.local.dao.TVShowDao
import com.gscarlos.tvshowscarlosg.data.local.model.TVShowEntity

@Database(
    entities = [TVShowEntity::class],
    version = 1
)
abstract class AppDatabase() : RoomDatabase() {
    abstract fun tvShowDao(): TVShowDao
}
