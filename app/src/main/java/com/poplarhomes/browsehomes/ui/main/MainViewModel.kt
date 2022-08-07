package com.poplarhomes.browsehomes.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.poplarhomes.browsehomes.GetBuildingsQuery
import com.poplarhomes.browsehomes.domain.GetBuildingsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getBuildingsUseCase: GetBuildingsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<MainState>(MainState.HideLoadingState)
    val state: StateFlow<MainState> = _state

    private var buildings = emptyList<GetBuildingsQuery.Building?>()

    init {
        getBuildings()
    }

    private fun getBuildings() {
        viewModelScope.launch {
            _state.emit(MainState.ShowLoadingState)

            try {
                buildings = getBuildingsUseCase.execute()
                _state.emit(MainState.GetBuildings(buildings))
            } catch (e: Exception) {
                e.printStackTrace()
            }

            _state.emit(MainState.HideLoadingState)
        }
    }

    fun filterBuildings(query: String) {
        _state.value = MainState.GetBuildings(
            if (query.isBlank()) buildings
            else buildings.filter { building ->
                building?.address?.lowercase()?.contains(query) ?: false
            }
        )
    }

}
