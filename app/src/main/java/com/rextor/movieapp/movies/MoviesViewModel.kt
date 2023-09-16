package com.rextor.movieapp.movies


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rextor.movieapp.LocalStrorage.Model.favouriteEntity
import com.rextor.movieapp.Model.Tittles
import com.rextor.movieapp.Model.Type
import com.rextor.movieapp.Repository.Repository
import com.rextor.movieapp.Utils.coroutineexceptionhandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class MoviesViewModel @Inject constructor(
    @Named("Movies")
    private val repository: Repository,
    private val coroutineexceptionhandler: coroutineexceptionhandler
): ViewModel() {
    init {
        getlistOfMovies()
    }
    var _listOfMovies = MutableStateFlow<Tittles>(Tittles(
        listofTitles = listOf()
    ))
    val listOfMovies : StateFlow<Tittles>
        get() = _listOfMovies
    fun getlistOfMovies(){
        val result = viewModelScope.async (coroutineexceptionhandler.coroutineExceptionHandler){
            repository.getList(
                type = Type.movies.type
            )

        }

        viewModelScope.launch (coroutineexceptionhandler.coroutineExceptionHandler){
        if (
            result.await() != null
        ){
            _listOfMovies.emit(result.await()!!)
        }

        }

    }

    fun insertfav(favouriteEntity: favouriteEntity){
        viewModelScope.launch (coroutineexceptionhandler.coroutineExceptionHandler){
            repository.insertfav(favouriteEntity)
        }
    }
}