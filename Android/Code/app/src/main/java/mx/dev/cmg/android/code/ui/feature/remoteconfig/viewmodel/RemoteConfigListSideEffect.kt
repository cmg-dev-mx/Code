package mx.dev.cmg.android.code.ui.feature.remoteconfig.viewmodel

sealed interface RemoteConfigListSideEffect {
    data object NavigateBack : RemoteConfigListSideEffect
}