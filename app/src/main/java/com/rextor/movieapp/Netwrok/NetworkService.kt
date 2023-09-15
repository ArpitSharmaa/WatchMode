package com.rextor.movieapp.Netwrok

import com.rextor.movieapp.Model.TitleDetails
import com.rextor.movieapp.Model.Tittles
import com.rextor.movieapp.Model.listOFOTT
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


const val API_KEY = "sTLGdDGqG4tRsGLvGLMDbGSXI4BdHlz3jY06NwS9"

interface NetworkService {

    @GET("/v1/list-titles/")
    suspend fun getList(
        @Query("apiKey") apikey:String,
        @Query("types") type:String
    ):Response<Tittles>

    @GET("/v1/title/{title_id}/details/")
    suspend fun getDetails(
        @Path("title_id") titleId:String,
        @Query("apiKey") apikey:String
    ):Response<TitleDetails>

    @GET("/v1/sources/")
    suspend fun getOttPlatforms(
        @Query("apiKey") apikey: String
    ):Response<List<listOFOTT>>


}