package mx.dev.cmg.android.code.ui.feature.datapersistence.viewmodel

import mx.dev.cmg.android.code.domain.Note

data class DataPersistenceUiState(
    val isLoading: Boolean = false,
    val notes: List<Note> = emptyList()
)
