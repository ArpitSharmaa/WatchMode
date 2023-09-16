package com.rextor.movieapp.Repository

import com.rextor.movieapp.LocalStrorage.Model.favouriteEntity
import com.rextor.movieapp.Model.TitleDetails
import com.rextor.movieapp.Model.Tittles
import com.rextor.movieapp.Model.listOFOTT

interface Repository {
    suspend fun getList(type:String):Tittles?
    suspend fun getDetails(title_id :String):TitleDetails?
    suspend fun getOTTplatforms():List<listOFOTT>?
    suspend fun getfav():List<favouriteEntity>
    suspend fun insertfav(favouriteEntity: favouriteEntity)
}