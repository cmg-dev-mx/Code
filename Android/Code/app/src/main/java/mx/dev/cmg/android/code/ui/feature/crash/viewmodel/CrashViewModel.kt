package mx.dev.cmg.android.code.ui.feature.crash.viewmodel

import mx.dev.cmg.android.code.ui.base.viewmodel.MviViewModel


class CrashViewModel : MviViewModel<Unit, CrashEvent, CrashSideEffect>(
    initialState = Unit
) {
    override suspend fun handleEvent(event: CrashEvent) = when (event) {
        is CrashEvent.OnCrashButtonClicked -> throw RuntimeException("¡Esto es un crash de ejemplo!")
        is CrashEvent.NavigateBack -> emitSideEffect(CrashSideEffect.NavigateBack)
    }
}