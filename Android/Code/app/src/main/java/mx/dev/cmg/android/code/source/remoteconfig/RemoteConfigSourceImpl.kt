package mx.dev.cmg.android.code.source.remoteconfig

import com.google.firebase.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.remoteConfig
import com.google.firebase.remoteconfig.remoteConfigSettings
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withTimeoutOrNull
import kotlin.time.Duration.Companion.seconds

class RemoteConfigSourceImpl : RemoteConfigSource {

    private val remoteConfig: FirebaseRemoteConfig by lazy { Firebase.remoteConfig }
    
    private val configSettings by lazy {
        remoteConfigSettings {
            minimumFetchIntervalInSeconds = 3600
        }
    }

    private val fetchMutex = Mutex()

    @Volatile
    private var isFetchCompleted = false

    init {
        remoteConfig.setDefaultsAsync(
            mapOf(
                "mvi" to false,
                "crash" to false
            )
        )
        remoteConfig.setConfigSettingsAsync(configSettings)
    }

    private suspend fun ensureFetchCompleted(): Boolean {
        if (isFetchCompleted) return true

        return fetchMutex.withLock {
            if (isFetchCompleted) return true

            try {
                val success = withTimeoutOrNull(10.seconds) {
                    remoteConfig.fetchAndActivate().await()
                } ?: false

                isFetchCompleted = true
                success
            } catch (e: Exception) {
                isFetchCompleted = true
                false
            }
        }
    }

    override suspend fun getBoolean(key: String): Boolean {
        ensureFetchCompleted()
        
        return try {
            remoteConfig.getBoolean(key)
        } catch (_: Exception) {
            false
        }
    }
}