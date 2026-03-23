package mx.dev.cmg.android.code.data.repository.ai

import com.google.firebase.ai.type.PublicPreviewAPI
import mx.dev.cmg.android.code.data.datasource.ai.AiDataSource

@OptIn(PublicPreviewAPI::class)
class AiRepositoryImpl(
    private val aiDataSource: AiDataSource
) : AiRepository {

    override suspend fun connect() {
        aiDataSource.connect()
    }

    override suspend fun startAudioConversation() {
        aiDataSource.startAudioConversation()
    }

    override suspend fun stopAudioConversation() {
        aiDataSource.stopAudioConversation()
    }
}

