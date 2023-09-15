package com.rextor.movieapp.Model

data class TitleDetails(
    val id : String,
    val original_title : String,
    val plot_overview : String,
    val type : String,
    val release_date : String,
    val user_rating : Float,
    val poster : String,
    val backdrop : String,
    val trailer : String?,
    val trailer_thumbnail : String?
)
