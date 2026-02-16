package mx.dev.cmg.android.code.datasource.remote.remoteconfig

interface RemoteConfigDataSource {
    suspend fun getBoolean(key: String): Boolean
}
