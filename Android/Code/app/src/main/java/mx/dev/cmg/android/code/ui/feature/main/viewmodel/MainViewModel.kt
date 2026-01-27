package mx.dev.cmg.android.code.ui.feature.main.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    var uiState by mutableStateOf(MainUiState())
        private set

    fun onEvent(event: MainEvent) {
        when (event) {
            is MainEvent.OnLoad -> {
                loadData()
            }
        }
    }

    private fun loadData() {
        viewModelScope.launch {
            uiState = uiState.copy(isLoading = true)
            delay(2000)
            uiState = uiState.copy(name = "Code", isLoading = false)
        }
    }
}