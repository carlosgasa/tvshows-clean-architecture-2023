package com.gscarlos.tvshowscarlosg.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tvshows")
data class TVShowEntity(
    @PrimaryKey
    val id: Long,
    val name: String,
    val network: String,
    val dates: String,
    val imageMedium: String,
)
