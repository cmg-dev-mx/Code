package mx.dev.cmg.android.code.repository.feature

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import mx.dev.cmg.android.code.core.model.Feature
import mx.dev.cmg.android.code.source.remoteconfig.RemoteConfigSource

class FeatureRepositoryImpl(
    private val source: RemoteConfigSource
) : FeatureRepository {

    override fun getAvailableFeatures(): Flow<List<Feature>> = flow {
        emit(buildList {
            if (source.getBoolean("mvi")) {
                add(Feature.MVI)
            }
        })
    }
}