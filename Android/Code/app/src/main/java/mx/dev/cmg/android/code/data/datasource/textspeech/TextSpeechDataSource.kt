package mx.dev.cmg.android.code.data.datasource.textspeech

interface TextSpeechDataSource {
    fun speak(text: String): Result<Unit>
}

