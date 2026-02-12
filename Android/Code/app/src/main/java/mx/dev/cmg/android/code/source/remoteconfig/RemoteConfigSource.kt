package mx.dev.cmg.android.code.source.remoteconfig

interface RemoteConfigSource {
    suspend fun getBoolean(key: String): Boolean
}