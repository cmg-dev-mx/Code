package mx.dev.cmg.android.code.ui.base.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

fun ViewModel.launchEvent(block: suspend () -> Unit) {
    viewModelScope.launch {
        block()
    }
}

fun <T> MutableStateFlow<T>.update(reducer: T.() -> T) {
    value = value.reducer()
}

suspend fun <T> Channel<T>.sendEffect(effect: T) {
    send(effect)
}
