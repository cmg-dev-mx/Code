package mx.dev.cmg.android.code.source.remoteconfig

import com.google.firebase.Firebase
import com.google.firebase.remoteconfig.remoteConfig
import com.google.firebase.remoteconfig.remoteConfigSettings

class RemoteConfigSourceImpl : RemoteConfigSource {

    private val remoteConfig by lazy { Firebase.remoteConfig }
    private val configSettings by lazy {
        remoteConfigSettings {
            minimumFetchIntervalInSeconds = 3600
        }
    }

    init {
        remoteConfig.setConfigSettingsAsync(configSettings)
        remoteConfig.fetchAndActivate()
    }

    override suspend fun getBoolean(key: String) = try {
        remoteConfig.getBoolean(key)
    } catch (_: Exception) {
        false
    }
}