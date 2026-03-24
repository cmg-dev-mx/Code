package mx.dev.cmg.android.code.data.repository.textspeech

import mx.dev.cmg.android.code.data.datasource.textspeech.TextSpeechDataSource

class TextSpeechRepositoryImpl(
    private val textSpeechDataSource: TextSpeechDataSource
) : TextSpeechRepository {

    override fun speak(text: String): Result<Unit> {
        return textSpeechDataSource.speak(text)
    }
}

