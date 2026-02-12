package mx.dev.cmg.android.code.ui.feature.main.viewmodel

import mx.dev.cmg.android.code.ui.feature.main.model.FeatureUI

data class MainUiState(
    val isLoading: Boolean = false,
    val availableFeatures: List<FeatureUI> = emptyList()
)
