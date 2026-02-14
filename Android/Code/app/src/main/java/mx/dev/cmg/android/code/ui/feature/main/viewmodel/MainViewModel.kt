package mx.dev.cmg.android.code.ui.feature.main.viewmodel

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import mx.dev.cmg.android.code.repository.feature.FeatureRepository
import mx.dev.cmg.android.code.ui.base.viewmodel.MviViewModel
import mx.dev.cmg.android.code.ui.feature.main.model.FeatureUI

class MainViewModel(
    private val repository: FeatureRepository
) : MviViewModel<MainUiState, MainEvent, MainSideEffect>(
    initialState = MainUiState()
) {
    init {
        loadData()
    }

    override suspend fun handleEvent(event: MainEvent) = when (event) {
        is MainEvent.NavigateToNameList -> emitSideEffect(MainSideEffect.NavigateToNameList)
        is MainEvent.NavigateToCrashlytics -> emitSideEffect(MainSideEffect.NavigateToCrashlytics)
    }

    private fun loadData() {
        viewModelScope.launch {
            repository.getAvailableFeatures().collect { features ->
                val featuresUI = features.map { FeatureUI.from(it) }

                updateState {
                    copy(availableFeatures = featuresUI)
                }
            }
        }
    }
}