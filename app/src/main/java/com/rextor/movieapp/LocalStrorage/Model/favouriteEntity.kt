package com.rextor.movieapp.LocalStrorage.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourite")
data class favouriteEntity(
    @PrimaryKey
    val id : String,
    val type : String,
    val release_date : String,
    val original_title : String,
)
