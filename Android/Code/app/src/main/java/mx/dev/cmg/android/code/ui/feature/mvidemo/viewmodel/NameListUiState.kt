package mx.dev.cmg.android.code.ui.feature.mvidemo.viewmodel

import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf

data class NameListUiState(
    val names: PersistentList<String> = persistentListOf(),
    val isLoading: Boolean = false,
    val typedName: String = ""
)