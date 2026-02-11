package mx.dev.cmg.android.code.ui.feature.remoteconfig.viewmodel

import mx.dev.cmg.android.code.core.model.RemoteConfigItem

data class RemoteConfigListUiState(
    val isLoading: Boolean = false,
    val remoteConfigItems: List<RemoteConfigItem> = emptyList(),
)