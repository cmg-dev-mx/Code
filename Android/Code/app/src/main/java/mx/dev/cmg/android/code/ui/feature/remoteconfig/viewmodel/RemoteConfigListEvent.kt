package mx.dev.cmg.android.code.ui.feature.remoteconfig.viewmodel

sealed interface RemoteConfigListEvent {
    data object OnBackClick : RemoteConfigListEvent
    data class OnToggleRemoteConfig(val id: String) : RemoteConfigListEvent
}