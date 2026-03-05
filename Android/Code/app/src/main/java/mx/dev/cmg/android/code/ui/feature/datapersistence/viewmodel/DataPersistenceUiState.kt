package mx.dev.cmg.android.code.ui.feature.datapersistence.viewmodel

import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import mx.dev.cmg.android.code.domain.Note

data class DataPersistenceUiState(
    val isLoading: Boolean = false,
    val notes: PersistentList<Note> = persistentListOf()
)
