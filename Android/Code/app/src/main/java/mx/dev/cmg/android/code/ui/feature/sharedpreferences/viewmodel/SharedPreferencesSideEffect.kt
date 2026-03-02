package mx.dev.cmg.android.code.ui.feature.sharedpreferences.viewmodel

sealed interface SharedPreferencesSideEffect {
    data object NavigateBack : SharedPreferencesSideEffect
    data object SaveSuccess : SharedPreferencesSideEffect
}
