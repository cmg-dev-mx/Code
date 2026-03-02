package mx.dev.cmg.android.code.data.datasource.local.shared

class SharedPreferencesLocalSourceImpl(
    private val appPreferences: AppPreferences
) : SharedPreferencesLocalSource {

    override suspend fun getValue(): String {
        return appPreferences.getString(KEY_VALUE_STRING)
    }

    override suspend fun isEditEnabled(): Boolean {
        return appPreferences.getBoolean(KEY_EDIT_ENABLED)
    }

    override suspend fun updateEditEnabled(enabled: Boolean) {
        appPreferences.putBoolean(KEY_EDIT_ENABLED, enabled)
    }

    override suspend fun updateValue(newValue: String) {
        appPreferences.putString(KEY_VALUE_STRING, newValue)
    }

    companion object {
        private const val KEY_VALUE_STRING = "value_string"
        private const val KEY_EDIT_ENABLED = "edit_enabled"
    }
}