package com.rextor.movieapp.favourites

import android.util.Log
import com.rextor.movieapp.LocalStrorage.Model.favouriteEntity
import com.rextor.movieapp.LocalStrorage.database.database
import com.rextor.movieapp.Model.TitleDetails
import com.rextor.movieapp.Model.Tittles
import com.rextor.movieapp.Model.listOFOTT
import com.rextor.movieapp.Repository.Repository
import javax.inject.Inject

class favrepository@Inject constructor(
    private val database: database
) :Repository{
    val dao = database.getdao()

    override suspend fun insertfav(favouriteEntity: favouriteEntity){
       dao.insertfavourite(favouriteEntity)
    }

    override suspend fun getList(type: String): Tittles? {
        return null
    }

    override suspend fun getDetails(title_id: String): TitleDetails? {
       return null
    }

    override suspend fun getOTTplatforms(): List<listOFOTT>? {
       return null
    }

    override suspend fun getfav():List<favouriteEntity>{

       return dao.getfavourites()
    }


}