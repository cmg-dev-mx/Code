package mx.dev.cmg.android.code.data.repository.shared

interface SharedPreferencesRepository {
    suspend fun getValue(): String
    suspend fun isEditEnabled(): Boolean
    suspend fun updateEditEnabled(enabled: Boolean)
    suspend fun updateValue(newValue: String)
}
