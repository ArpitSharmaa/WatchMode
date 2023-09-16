package com.rextor.movieapp.TVshows

import com.rextor.movieapp.LocalStrorage.Model.favouriteEntity
import com.rextor.movieapp.LocalStrorage.database.database
import com.rextor.movieapp.Model.TitleDetails
import com.rextor.movieapp.Model.Tittles
import com.rextor.movieapp.Model.listOFOTT
import com.rextor.movieapp.Netwrok.API_KEY
import com.rextor.movieapp.Netwrok.NetworkService
import com.rextor.movieapp.Repository.Repository
import javax.inject.Inject

class tv_ShowRepository @Inject constructor(
    private val ApiServices :NetworkService,
    private val database: database
) : Repository {
    override suspend fun getList(type: String): Tittles? {
        val response = ApiServices.getList(API_KEY,type)
        if (response.isSuccessful){
            return response.body()
        }else{
            return null
        }
    }

    override suspend fun getDetails(title_id: String): TitleDetails?{
       return null
    }

    override suspend fun getOTTplatforms(): List<listOFOTT>? {
        return null
    }

    override suspend fun getfav(): List<favouriteEntity> {
        return emptyList()
    }

    override suspend fun insertfav(favouriteEntity: favouriteEntity) {
        database.getdao().insertfavourite(favouriteEntity)
    }


}