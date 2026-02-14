package mx.dev.cmg.android.code.ui.feature.mvidemo.viewmodel

import kotlinx.coroutines.delay
import mx.dev.cmg.android.code.ui.base.viewmodel.MviViewModel
import kotlin.time.Duration.Companion.seconds

class NameListViewModel : MviViewModel<NameListUiState, NameListEvent, NameListSideEffect>(
    initialState = NameListUiState()
) {
    override suspend fun handleEvent(event: NameListEvent) = when (event) {
        is NameListEvent.TypedNameChange -> updateTypedName(event.newName)
        is NameListEvent.AddName -> addName()
        is NameListEvent.NavigateBack -> emitSideEffect(NameListSideEffect.NavigateBack)
    }

    private fun updateTypedName(newName: String) {
        updateState {
            copy(typedName = newName)
        }
    }

    private suspend fun addName() {
        val name = currentState.typedName
        if (name.isBlank()) return

        updateState {
            copy(isLoading = true, typedName = "")
        }

        delay(1.seconds) // Simulate network request

        val newList = arrayListOf<String>()
        newList.addAll(currentState.names)
        newList.add(name)

        updateState {
            copy(
                names = newList,
                isLoading = false
            )
        }
    }
}