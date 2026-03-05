package mx.dev.cmg.android.code.ui.feature.main.viewmodel

import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import mx.dev.cmg.android.code.ui.feature.main.model.FeatureUI

data class MainUiState(
    val isLoading: Boolean = false,
    val availableFeatures: PersistentList<FeatureUI> = persistentListOf()
)
