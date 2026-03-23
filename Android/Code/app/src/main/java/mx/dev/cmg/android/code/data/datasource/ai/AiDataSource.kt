package mx.dev.cmg.android.code.data.datasource.ai

import com.google.firebase.ai.type.PublicPreviewAPI

@OptIn(PublicPreviewAPI::class)
interface AiDataSource {
    suspend fun connect()
    suspend fun startAudioConversation()
    suspend fun stopAudioConversation()
}

