package com.rextor.movieapp.Utils

import android.content.Context
import android.widget.Toast
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineExceptionHandler
import javax.inject.Inject

class coroutineexceptionhandler @Inject constructor(
     context: Context
) {
    val coroutineExceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        Toast.makeText(context, throwable.localizedMessage, Toast.LENGTH_SHORT).show()
    }
}