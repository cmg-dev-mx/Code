package mx.dev.cmg.android.code.ui.feature.datapersistence.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import mx.dev.cmg.android.code.ui.base.viewmodel.launchEvent
import mx.dev.cmg.android.code.ui.base.viewmodel.sendEffect
import mx.dev.cmg.android.code.ui.base.viewmodel.update

class DataPersistenceViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(DataPersistenceUiState())
    val uiState: StateFlow<DataPersistenceUiState> = _uiState.asStateFlow()

    private val _sideEffect = Channel<DataPersistenceSideEffect>(Channel.BUFFERED)
    val sideEffect = _sideEffect.receiveAsFlow()

    fun onEvent(event: DataPersistenceEvent) = launchEvent {
        when (event) {
            is DataPersistenceEvent.NavigateBack -> _sideEffect.sendEffect(DataPersistenceSideEffect.NavigateBack)
        }
    }

    private fun updateState() {
        _uiState.update {
            copy(isLoading = true)
        }
    }
}
