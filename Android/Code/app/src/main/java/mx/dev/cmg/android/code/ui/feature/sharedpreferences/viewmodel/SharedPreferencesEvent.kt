package mx.dev.cmg.android.code.ui.feature.sharedpreferences.viewmodel

sealed interface SharedPreferencesEvent {
    data object NavigateBack : SharedPreferencesEvent
    data class ToggleEdit(val enabled: Boolean) : SharedPreferencesEvent
    data class OnValueChange(val value: String) : SharedPreferencesEvent
    data object SaveValue : SharedPreferencesEvent
}
