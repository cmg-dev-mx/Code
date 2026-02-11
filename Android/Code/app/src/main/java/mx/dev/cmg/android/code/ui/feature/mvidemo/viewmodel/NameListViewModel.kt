package mx.dev.cmg.android.code.ui.feature.mvidemo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

class NameListViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(NameListUiState())
    val uiState = _uiState.asStateFlow()

    private val _sideEffect = Channel<NameListSideEffect>(Channel.BUFFERED)
    val sideEffect = _sideEffect.receiveAsFlow()

    fun onEvent(event: NameListEvent) {
        viewModelScope.launch {
            when (event) {
                is NameListEvent.NavigateBack -> navigateBack()
                is NameListEvent.TypedNameChange -> updateTypedName(event.newName)
                is NameListEvent.AddName -> addName()
            }
        }
    }

    private suspend fun navigateBack() {
        _sideEffect.send(NameListSideEffect.NavigateBack)
    }

    private suspend fun updateTypedName(newName: String) {
        _uiState.value = _uiState.value.copy(typedName = newName)
    }

    private suspend fun addName() {
        _uiState.value = _uiState.value.copy(isLoading = true)
        delay(1.seconds)
        val newList = arrayListOf<String>()
        newList.addAll(_uiState.value.names)
        newList.add(_uiState.value.typedName)
        _uiState.value = _uiState.value.copy(
            names = newList,
            typedName = "",
            isLoading = false
        )
    }
}