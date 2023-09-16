package com.rextor.movieapp.favourites

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rextor.movieapp.LocalStrorage.Model.favouriteEntity
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
class favviewmodel @Inject constructor(
    @Named("room")
    private val favrepository: Repository,
    private val coroutineexceptionhandler: coroutineexceptionhandler
):ViewModel(){

    init {
        getlist()
    }
    var _listoffav = MutableStateFlow<List<favouriteEntity>>(emptyList())
    val listoffav : StateFlow<List<favouriteEntity>>
        get() = _listoffav

    fun getlist(){
       val result = viewModelScope.async(coroutineexceptionhandler.coroutineExceptionHandler) {
            favrepository.getfav()

        }
        viewModelScope.launch(coroutineexceptionhandler.coroutineExceptionHandler) {
            if (result.await().isNotEmpty()){
                _listoffav.emit(result.await())
            }

        }
    }

}