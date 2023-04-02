package com.gscarlos.tvshowscarlosg.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gscarlos.tvshowscarlosg.data.local.model.TVShowEntity
import com.gscarlos.tvshowscarlosg.domain.model.TVShow
import kotlinx.coroutines.flow.Flow

@Dao
interface TVShowDao {

    @Query("SELECT EXISTS (SELECT id FROM tvshows WHERE id=:id)")
    fun tvShowExistsById(id: Long): Boolean

    @Query("DELETE FROM tvshows WHERE id=:id")
    fun deleteById(id: Long)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(tvShow: TVShowEntity)

    @Query("SELECT * FROM tvshows")
    fun getTVShows(): Flow<List<TVShowEntity>>
}