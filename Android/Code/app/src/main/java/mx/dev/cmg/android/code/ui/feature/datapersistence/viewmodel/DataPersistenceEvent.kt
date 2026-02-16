package mx.dev.cmg.android.code.ui.feature.datapersistence.viewmodel

sealed interface DataPersistenceEvent {
    data object NavigateBack : DataPersistenceEvent
    data object CreateNote: DataPersistenceEvent
}
