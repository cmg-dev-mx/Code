package mx.dev.cmg.android.code.data.repository.ai

import com.google.firebase.ai.type.PublicPreviewAPI
import mx.dev.cmg.android.code.data.datasource.ai.AiDataSource
import mx.dev.cmg.android.code.data.datasource.remoteconfig.RemoteConfigDataSource
import mx.dev.cmg.android.code.domain.AiConfig

@OptIn(PublicPreviewAPI::class)
class AiRepositoryImpl(
    private val aiDataSource: AiDataSource,
    private val remoteConfigDataSource: RemoteConfigDataSource
) : AiRepository {

    override suspend fun initialize(): Result<Boolean> {
        val modelName = remoteConfigDataSource.getString(AiConfig.MODEL.key)

        if (modelName.isBlank()) {
            return Result.failure(IllegalStateException("AI model name is not configured in Remote Config"))
        }

        return aiDataSource.initialize(modelName)
    }

    override suspend fun startAudioConversation() {
        aiDataSource.startAudioConversation()
    }

    override suspend fun stopAudioConversation() {
        aiDataSource.stopAudioConversation()
    }
}
