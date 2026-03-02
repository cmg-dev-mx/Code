package mx.dev.cmg.android.code.data.mapper

import mx.dev.cmg.android.code.domain.Feature

object FeatureMapper {
    
    fun String.toFeature(): Feature? {
        return when (this) {
            "mvi" -> Feature.MVI
            "crash" -> Feature.CRASHLYTICS
            "persistence" -> Feature.PERSISTENCE
            else -> null
        }
    }
}
