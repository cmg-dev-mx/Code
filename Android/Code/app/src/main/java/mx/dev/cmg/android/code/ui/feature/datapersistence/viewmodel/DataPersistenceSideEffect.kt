package mx.dev.cmg.android.code.ui.feature.datapersistence.viewmodel

sealed interface DataPersistenceSideEffect {
    data object NavigateBack : DataPersistenceSideEffect
}
