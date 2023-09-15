package com.rextor.movieapp.SourcesOTT

import com.rextor.movieapp.Model.TitleDetails
import com.rextor.movieapp.Model.Tittles
import com.rextor.movieapp.Model.listOFOTT
import com.rextor.movieapp.Netwrok.API_KEY
import com.rextor.movieapp.Netwrok.NetworkService
import com.rextor.movieapp.Repository.Repository
import javax.inject.Inject

class sourceottRepository @Inject constructor(
    private val networkService: NetworkService
) : Repository {
    override suspend fun getList(type: String): Tittles? {
        return null
    }

    override suspend fun getDetails(title_id: String): TitleDetails? {
        return null
    }

    override suspend fun getOTTplatforms(): List<listOFOTT>? {
        val response = networkService.getOttPlatforms(API_KEY)
        if (response.isSuccessful){
            return response.body()
        }else{
            return null
        }
    }

}