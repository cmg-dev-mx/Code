package mx.dev.cmg.android.code.repository.feature

import kotlinx.coroutines.flow.Flow
import mx.dev.cmg.android.code.core.model.Feature

interface FeatureRepository {
    fun getAvailableFeatures(): Flow<List<Feature>>
}