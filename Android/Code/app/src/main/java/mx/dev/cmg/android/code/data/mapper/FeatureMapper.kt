package mx.dev.cmg.android.code.data.mapper

import mx.dev.cmg.android.code.core.model.Feature

object FeatureMapper {
    
    fun String.toFeature(): Feature? {
        return when (this) {
            "mvi" -> Feature.MVI
            "crash" -> Feature.CRASHLYTICS
            else -> null
        }
    }
}
