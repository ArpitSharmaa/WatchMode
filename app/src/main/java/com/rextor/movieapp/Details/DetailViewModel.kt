package com.rextor.movieapp.Details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rextor.movieapp.Model.TitleDetails
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
class DetailViewModel @Inject constructor(
    @Named("Details")
    private val repository: Repository,
    private val coroutineexceptionhandler: coroutineexceptionhandler
):ViewModel(){
    var _details = MutableStateFlow<TitleDetails?>(null)
    val details : StateFlow<TitleDetails?>
        get() = _details

    fun getdetailsof(title_id:String){
        val result = viewModelScope.async(coroutineexceptionhandler.coroutineExceptionHandler) {
            repository.getDetails(title_id)
        }
        viewModelScope.launch (coroutineexceptionhandler.coroutineExceptionHandler){
            if (result.await()!=null){
                _details.emit(result.await())
            }
        }
    }
}