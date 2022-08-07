package com.poplarhomes.browsehomes.ui.property

sealed class AddPropertyState {

    object ShowLoadingState : AddPropertyState()

    object HideLoadingState : AddPropertyState()

    data class SetSubmitButton(val isComplete: Boolean) : AddPropertyState()

    object AddProperty : AddPropertyState()

    data class ShowErrorMessage(val errorMessage: String) : AddPropertyState()

}
