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

    private var address = ""
    private var beds: Int? = null
    private var baths: Int? = null
    private var rent: Double? = null

    fun setAddress(address: String) {
        this.address = address
        setSubmitButton()
    }

    fun setBedrooms(beds: Int?) {
        this.beds = beds
        setSubmitButton()
    }

    fun setBathrooms(baths: Int?) {
        this.baths = baths
        setSubmitButton()
    }

    fun setRent(rent: Double?) {
        this.rent = rent
        setSubmitButton()
    }

    private fun setSubmitButton() {
        _state.value = AddPropertyState.SetSubmitButton(
            address.isNotBlank()
                && beds != null
                && baths != null
                && rent != null
        )
    }

}
