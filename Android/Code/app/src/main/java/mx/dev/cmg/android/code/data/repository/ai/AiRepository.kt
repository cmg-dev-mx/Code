package mx.dev.cmg.android.code.data.repository.ai

import com.google.firebase.ai.type.PublicPreviewAPI

@OptIn(PublicPreviewAPI::class)
interface AiRepository {
    suspend fun connect()
    suspend fun startAudioConversation()
    suspend fun stopAudioConversation()
}

