package mx.dev.cmg.android.code.data.repository.feature

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import mx.dev.cmg.android.code.data.datasource.remoteconfig.RemoteConfigDataSource
import mx.dev.cmg.android.code.domain.Feature

class FeatureRepositoryImpl(
    private val remoteConfigDataSource: RemoteConfigDataSource
) : FeatureRepository {

    override fun getAvailableFeatures(): Flow<List<Feature>> = flow {
        val availableFeatures = buildList {
            Feature.entries.forEach { feature ->
                if (remoteConfigDataSource.getBoolean(feature.key)) {
                    add(feature)
                }
            }
        }
        
        emit(availableFeatures)
    }
}