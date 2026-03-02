package mx.dev.cmg.android.code.data.datasource.remoteconfig

interface RemoteConfigDataSource {
    suspend fun getBoolean(key: String): Boolean
}
