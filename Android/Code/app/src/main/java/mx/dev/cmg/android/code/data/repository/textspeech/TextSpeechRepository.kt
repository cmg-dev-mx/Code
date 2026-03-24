package mx.dev.cmg.android.code.data.repository.textspeech

interface TextSpeechRepository {
    fun speak(text: String): Result<Unit>
}

