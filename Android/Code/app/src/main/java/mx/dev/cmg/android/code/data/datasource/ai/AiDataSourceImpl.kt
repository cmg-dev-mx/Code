package mx.dev.cmg.android.code.data.datasource.ai

import android.annotation.SuppressLint
import com.google.firebase.Firebase
import com.google.firebase.ai.ai
import com.google.firebase.ai.type.GenerativeBackend
import com.google.firebase.ai.type.LiveSession
import com.google.firebase.ai.type.PublicPreviewAPI
import com.google.firebase.ai.type.ResponseModality
import com.google.firebase.ai.type.liveGenerationConfig

@OptIn(PublicPreviewAPI::class)
class AiDataSourceImpl : AiDataSource {

    private val liveModel = Firebase.ai(backend = GenerativeBackend.googleAI())
        .liveModel(
            modelName = "gemini-2.5-flash-native-audio-preview-12-2025",
            generationConfig = liveGenerationConfig {
                responseModality = ResponseModality.AUDIO
            }
        )

    private lateinit var session: LiveSession

    override suspend fun connect() {
        session = liveModel.connect()
    }

    @SuppressLint("MissingPermission")
    override suspend fun startAudioConversation() {
        session.startAudioConversation()
    }

    override suspend fun stopAudioConversation() {
        session.stopAudioConversation()
    }
}