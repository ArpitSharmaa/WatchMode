package com.rextor.movieapp.Details

import com.rextor.movieapp.Model.TitleDetails
import com.rextor.movieapp.Model.Tittles
import com.rextor.movieapp.Model.listOFOTT
import com.rextor.movieapp.Netwrok.API_KEY
import com.rextor.movieapp.Netwrok.NetworkService
import com.rextor.movieapp.Repository.Repository
import javax.inject.Inject

class Details_repository @Inject constructor(
    private val networkService: NetworkService
):Repository {
    override suspend fun getList(type: String): Tittles? {
        return null
    }


    override suspend fun getDetails(title_id: String): TitleDetails? {
        val result = networkService.getDetails(
            apikey = API_KEY,
            titleId = title_id
        )
        if (result.isSuccessful){
            return result.body()
        }else{
            return null
        }
    }

    override suspend fun getOTTplatforms(): List<listOFOTT>? {
        return null
    }

}