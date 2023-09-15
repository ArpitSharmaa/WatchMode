package com.rextor.movieapp.Utils

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

object CurrentPosition {
    var _positions = MutableStateFlow<Positions>(Positions.HOME)
    val positions : StateFlow<Positions>
        get() = _positions
}