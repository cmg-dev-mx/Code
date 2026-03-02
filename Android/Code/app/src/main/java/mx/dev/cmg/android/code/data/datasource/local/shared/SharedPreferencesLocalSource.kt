package mx.dev.cmg.android.code.data.datasource.local.shared

interface SharedPreferencesLocalSource {
    suspend fun getValue(): String
    suspend fun isEditEnabled(): Boolean
    suspend fun updateEditEnabled(enabled: Boolean)
    suspend fun updateValue(newValue: String)
}
