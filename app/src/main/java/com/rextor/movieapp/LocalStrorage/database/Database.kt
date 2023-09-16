package com.rextor.movieapp.LocalStrorage.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rextor.movieapp.LocalStrorage.Model.favouriteEntity

@Database([favouriteEntity::class], version = 1)
abstract class database :RoomDatabase(){
    abstract fun getdao(): dao
}