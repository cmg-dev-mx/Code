package mx.dev.cmg.android.code.data.repository.feature

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import mx.dev.cmg.android.code.data.datasource.remoteconfig.RemoteConfigDataSource
import mx.dev.cmg.android.code.data.mapper.FeatureMapper.toFeature
import mx.dev.cmg.android.code.domain.Feature

class FeatureRepositoryImpl(
    private val remoteConfigDataSource: RemoteConfigDataSource
) : FeatureRepository {

    override fun getAvailableFeatures(): Flow<List<Feature>> = flow {
        val availableFeatures = buildList {
            listOf(
                "mvi",
                "crash",
                "persistence",
                "shared"
            ).forEach { key ->
                if (remoteConfigDataSource.getBoolean(key)) {
                    key.toFeature()?.let { feature -> add(feature) }
                }
            }
        }
        
        emit(availableFeatures)
    }
}