package mx.dev.cmg.android.code.data.datasource.textspeech

import android.content.Context
import android.speech.tts.TextToSpeech
import android.speech.tts.Voice
import java.util.Locale

class TextSpeechDataSourceImpl(context: Context) : TextSpeechDataSource {

    private var tts: TextToSpeech

    init {
        tts = TextToSpeech(context) { status ->
            if (status == TextToSpeech.SUCCESS) {
                tts.language = Locale.forLanguageTag("es-MX")
                tts.voice = Voice(
                    "es-es-x-sfb#female_2-local",
                    Locale.forLanguageTag("es-MX"),
                    Voice.QUALITY_HIGH,
                    Voice.LATENCY_NORMAL,
                    false,
                    null
                )
            }
        }
    }

    override fun speak(text: String): Result<Unit> {
        return try {
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
