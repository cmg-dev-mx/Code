package mx.dev.cmg.android.code.ui.feature.rest.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import mx.dev.cmg.android.code.ui.util.launchEvent
import mx.dev.cmg.android.code.ui.util.sendEffect
import mx.dev.cmg.android.code.ui.util.update
import kotlin.time.Duration.Companion.seconds

class RestViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(RestUiState())
    val uiState = _uiState.asStateFlow()

    private val _sideEffect = Channel<RestSideEffect>(Channel.BUFFERED)
    val sideEffect = _sideEffect.receiveAsFlow()

    fun onEvent(event: RestEvent) = launchEvent {
        when (event) {
            is RestEvent.NavigateBack -> _sideEffect.sendEffect(RestSideEffect.NavigateBack)
            is RestEvent.GetQuote -> fetchData()
        }
    }

    private suspend fun fetchData() {
        _uiState.update {
            copy(isLoading = true)
        }
        delay(2.seconds)
        _uiState.update {
            copy(
                isLoading = false,
                apiResponse = "This is a quote from the API",
            )
        }
        _sideEffect.sendEffect(RestSideEffect.ShowToast("Data fetched successfully"))
    }
}