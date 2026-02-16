package mx.dev.cmg.android.code.repository.feature

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import mx.dev.cmg.android.code.core.model.Feature
import mx.dev.cmg.android.code.data.mapper.FeatureMapper.toFeature
import mx.dev.cmg.android.code.datasource.remote.remoteconfig.RemoteConfigDataSource

class FeatureRepositoryImpl(
    private val remoteConfigDataSource: RemoteConfigDataSource
) : FeatureRepository {

    override fun getAvailableFeatures(): Flow<List<Feature>> = flow {
        val availableFeatures = buildList {
            listOf("mvi", "crash").forEach { key ->
                if (remoteConfigDataSource.getBoolean(key)) {
                    key.toFeature()?.let { feature -> add(feature) }
                }
            }
        }
        
        emit(availableFeatures)
    }
}