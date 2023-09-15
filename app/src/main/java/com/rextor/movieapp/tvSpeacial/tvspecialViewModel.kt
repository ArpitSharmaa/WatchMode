package com.rextor.movieapp.tvSpeacial

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
class tvspecialViewModel @Inject constructor(
    @Named("tvspeacial")
    private val repository: Repository,
    private val coroutineexceptionhandler: coroutineexceptionhandler
):ViewModel(){
    init {
        getlistOftvspeacial()
    }
    var _listOfTvspeacials = MutableStateFlow<Tittles>(
        Tittles(
            listofTitles = listOf()
        )
    )
    val listOftvspeacials : StateFlow<Tittles>
        get() = _listOfTvspeacials
    fun getlistOftvspeacial(){
        val result = viewModelScope.async (coroutineexceptionhandler.coroutineExceptionHandler){
            repository.getList(
                type = Type.tv_speacial.type
            )

        }

        viewModelScope.launch (coroutineexceptionhandler.coroutineExceptionHandler){
            if (
                result.await() != null
            ){
                _listOfTvspeacials.emit(result.await()!!)
            }

        }

    }
}