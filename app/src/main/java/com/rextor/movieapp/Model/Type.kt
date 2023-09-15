package com.rextor.movieapp.Model

sealed class Type(val type:String){
    object movies : Type(
        type = "movie")
    object tv_series:Type(
        type = "tv_series"
    )
    object tv_speacial:Type(
        type = "tv_special"
    )
}
