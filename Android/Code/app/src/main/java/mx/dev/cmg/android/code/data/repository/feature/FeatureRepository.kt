package mx.dev.cmg.android.code.data.repository.feature

import kotlinx.coroutines.flow.Flow
import mx.dev.cmg.android.code.domain.Feature

interface FeatureRepository {
    fun getAvailableFeatures(): Flow<List<Feature>>
}