package mx.dev.cmg.android.code.ui.feature.datapersistence.viewmodel

import mx.dev.cmg.android.code.core.model.Nota

data class DataPersistenceUiState(
    val isLoading: Boolean = false,
    val notes: List<Nota> = emptyList()
)
