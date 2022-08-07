package com.poplarhomes.browsehomes.ui.main

import com.poplarhomes.browsehomes.GetBuildingsQuery

sealed class MainState {

    object ShowLoadingState : MainState()

    object HideLoadingState : MainState()

    data class GetBuildings(val buildings: List<GetBuildingsQuery.Building?>) : MainState()

    data class ShowErrorMessage(val errorMessage: String) : MainState()

}
