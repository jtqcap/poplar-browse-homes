package com.poplarhomes.browsehomes.ui.property

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class AddPropertyViewModel @Inject constructor(
) : ViewModel() {

    private val _state = MutableStateFlow<AddPropertyState>(AddPropertyState.HideLoadingState)
    val state: StateFlow<AddPropertyState> = _state

}
