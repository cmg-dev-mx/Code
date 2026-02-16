package mx.dev.cmg.android.code.ui.feature.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import mx.dev.cmg.android.code.repository.feature.FeatureRepository
import mx.dev.cmg.android.code.ui.base.viewmodel.launchEvent
import mx.dev.cmg.android.code.ui.base.viewmodel.sendEffect
import mx.dev.cmg.android.code.ui.base.viewmodel.update
import mx.dev.cmg.android.code.ui.feature.main.model.FeatureUI

class MainViewModel(
    private val repository: FeatureRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    private val _sideEffect = Channel<MainSideEffect>(Channel.BUFFERED)
    val sideEffect = _sideEffect.receiveAsFlow()

    init {
        loadData()
    }

    fun onEvent(event: MainEvent) = launchEvent {
        when (event) {
            is MainEvent.NavigateToNameList -> _sideEffect.sendEffect(MainSideEffect.NavigateToNameList)
            is MainEvent.NavigateToCrashlytics -> _sideEffect.sendEffect(MainSideEffect.NavigateToCrashlytics)
            is MainEvent.NavigateToPersistence -> _sideEffect.sendEffect(MainSideEffect.NavigateToPersistence)
        }
    }

    private fun loadData() {
        viewModelScope.launch {
            _uiState.update {
                copy(isLoading = true)
            }
            repository.getAvailableFeatures().collect { features ->
                val featuresUI = features.map { FeatureUI.from(it) }

                _uiState.update {
                    copy(availableFeatures = featuresUI)
                }
            }
        }
    }
}