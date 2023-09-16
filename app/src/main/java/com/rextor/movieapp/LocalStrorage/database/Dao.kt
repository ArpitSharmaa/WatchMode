package com.rextor.movieapp.LocalStrorage.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rextor.movieapp.LocalStrorage.Model.favouriteEntity

@Dao
interface dao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertfavourite(favouriteEntity: favouriteEntity)

    @Query("SELECT * FROM favourite")
    suspend fun getfavourites():List<favouriteEntity>
}