package com.rextor.movieapp.Model

import com.google.gson.annotations.SerializedName

data class  Tittles(
    @SerializedName("titles")
    val listofTitles : List<title>
)
data class title(
    val id : String,
    val title : String,
    val year : Int
)
