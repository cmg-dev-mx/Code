package mx.dev.cmg.android.code.ui.feature.datapersistence.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import mx.dev.cmg.android.code.ui.base.viewmodel.launchEvent

class NoteDetailViewModel : ViewModel() {

    private val _sideEffect = Channel<NoteDetailSideEffect>(Channel.BUFFERED)
    val sideEffect = _sideEffect.receiveAsFlow()

    fun onEvent(event: NoteDetailEvent) = launchEvent {
        when (event) {
            is NoteDetailEvent.NavigateBack -> _sideEffect.send(NoteDetailSideEffect.NavigateBack)
        }
    }
}
