package com.poplarhomes.browsehomes.ui.property

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.poplarhomes.browsehomes.domain.AddPropertyUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddPropertyViewModel @Inject constructor(
    private val addPropertyUseCase: AddPropertyUseCase
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

    fun addProperty() {
        viewModelScope.launch {
            _state.emit(AddPropertyState.ShowLoadingState)

            try {
                addPropertyUseCase.execute(
                    address,
                    baths ?: 0,
                    beds ?: 0,
                    rent ?: 0.0
                )
                _state.emit(AddPropertyState.AddProperty)
            } catch (e: Exception) {
                e.printStackTrace()
            }

            _state.emit(AddPropertyState.HideLoadingState)
        }
    }

}
