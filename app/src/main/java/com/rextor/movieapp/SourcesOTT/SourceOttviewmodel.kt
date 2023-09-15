package com.rextor.movieapp.SourcesOTT

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rextor.movieapp.Model.listOFOTT
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
class SourceOttviewmodel @Inject constructor(
    @Named("OTT")
    private val repository: Repository,
    private val coroutineexceptionhandler: coroutineexceptionhandler
):ViewModel(){
    var _listofott = MutableStateFlow<List<listOFOTT>>(emptyList())
    val listofott : StateFlow<List<listOFOTT>>
        get() = _listofott

    init {
        getlistofott()
    }
    fun getlistofott(){
       val result=  viewModelScope.async (coroutineexceptionhandler.coroutineExceptionHandler){
            repository.getOTTplatforms()
        }
        viewModelScope.launch (coroutineexceptionhandler.coroutineExceptionHandler){
            if (result.await() != null){
                _listofott.emit(result.await()!!)
            }
        }

    }

}