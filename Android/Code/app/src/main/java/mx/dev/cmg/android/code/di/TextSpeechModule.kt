package mx.dev.cmg.android.code.di

import mx.dev.cmg.android.code.ui.feature.textspeech.viewmodel.TextSpeechViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val textSpeechModule = module {
    viewModelOf(::TextSpeechViewModel)
}