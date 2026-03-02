package mx.dev.cmg.android.code.data.repository.shared

import mx.dev.cmg.android.code.data.datasource.local.shared.SharedPreferencesLocalSource

class SharedPreferencesRepositoryImpl(
    private val localSource: SharedPreferencesLocalSource
) : SharedPreferencesRepository {

    override suspend fun getValue(): String {
        return localSource.getValue()
    }

    override suspend fun isEditEnabled(): Boolean {
        return localSource.isEditEnabled()
    }

    override suspend fun updateEditEnabled(enabled: Boolean) {
        localSource.updateEditEnabled(enabled)
    }

    override suspend fun updateValue(newValue: String) {
        localSource.updateValue(newValue)
    }
}