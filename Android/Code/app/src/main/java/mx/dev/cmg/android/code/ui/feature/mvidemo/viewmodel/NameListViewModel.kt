package mx.dev.cmg.android.code.ui.feature.mvidemo.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import mx.dev.cmg.android.code.ui.base.viewmodel.launchEvent
import mx.dev.cmg.android.code.ui.base.viewmodel.sendEffect
import mx.dev.cmg.android.code.ui.base.viewmodel.update
import kotlin.time.Duration.Companion.seconds

class NameListViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(NameListUiState())
    val uiState: StateFlow<NameListUiState> = _uiState.asStateFlow()

    private val _sideEffect = Channel<NameListSideEffect>(Channel.BUFFERED)
    val sideEffect = _sideEffect.receiveAsFlow()

    fun onEvent(event: NameListEvent) = launchEvent {
        when (event) {
            is NameListEvent.TypedNameChange -> updateTypedName(event.newName)
            is NameListEvent.AddName -> addName()
            is NameListEvent.NavigateBack -> _sideEffect.sendEffect(NameListSideEffect.NavigateBack)
        }
    }

    private fun updateTypedName(newName: String) {
        _uiState.update {
            copy(typedName = newName)
        }
    }

    private suspend fun addName() {
        val name = _uiState.value.typedName
        if (name.isBlank()) return

        _uiState.update {
            copy(isLoading = true, typedName = "")
        }

        delay(1.seconds)

        val newList = arrayListOf<String>()
        newList.addAll(_uiState.value.names)
        newList.add(name)

        _uiState.update {
            copy(
                names = newList,
                isLoading = false
            )
        }
    }
}