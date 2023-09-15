package com.rextor.movieapp.movies

import android.content.Context
import com.rextor.movieapp.Model.TitleDetails
import com.rextor.movieapp.Model.Tittles
import com.rextor.movieapp.Model.listOFOTT
import com.rextor.movieapp.Netwrok.API_KEY
import com.rextor.movieapp.Netwrok.NetworkService
import com.rextor.movieapp.Repository.Repository
import javax.inject.Inject

class Movie_list_repository @Inject constructor(
    private val ApiServices:NetworkService
): Repository {
    override suspend fun getList(type: String):Tittles? {
        val response = ApiServices.getList(API_KEY,type)
        if (response.isSuccessful){
            return response.body()
        }else{
            return null
        }
    }

    override suspend fun getDetails(title_id: String): TitleDetails?{
        val response = ApiServices.getDetails(API_KEY,title_id)
        if (response.isSuccessful){
            return response.body()
        }else{
            return null
        }
    }

    override suspend fun getOTTplatforms(): List<listOFOTT>? {
        return null
    }
}