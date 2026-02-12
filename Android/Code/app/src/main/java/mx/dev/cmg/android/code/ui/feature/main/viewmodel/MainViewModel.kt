package mx.dev.cmg.android.code.ui.feature.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import mx.dev.cmg.android.code.repository.feature.FeatureRepository
import mx.dev.cmg.android.code.ui.feature.main.model.FeatureUI

class MainViewModel(
    private val repository: FeatureRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(MainUiState())
    val uiState = _uiState.asStateFlow()

    private val _sideEffect = Channel<MainSideEffect>(Channel.BUFFERED)
    val sideEffect = _sideEffect.receiveAsFlow()

    init {
        viewModelScope.launch {
            loadData()
        }
    }

    fun onEvent(event: MainEvent) {
        viewModelScope.launch {
            when (event) {
                is MainEvent.NavigateToNameList -> navigateToRemoteConfigList()
                is MainEvent.NavigateToCrashlytics -> navigateToCrashlytics()
            }
        }
    }

    private suspend fun loadData() {
        repository.getAvailableFeatures().collect { features ->
            val featuresUI = features.map { FeatureUI.from(it) }

            _uiState.value = uiState.value.copy(
                availableFeatures = featuresUI
            )
        }
    }

    private suspend fun navigateToRemoteConfigList() {
        _sideEffect.send(MainSideEffect.NavigateToNameList)
    }

    private suspend fun navigateToCrashlytics() {
        _sideEffect.send(MainSideEffect.NavigateToCrashlytics)
    }
}