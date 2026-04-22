package mx.dev.cmg.android.code.di

import mx.dev.cmg.android.code.data.datasource.textspeech.TextSpeechDataSource
import mx.dev.cmg.android.code.data.datasource.textspeech.TextSpeechDataSourceImpl
import mx.dev.cmg.android.code.data.repository.textspeech.TextSpeechRepository
import mx.dev.cmg.android.code.data.repository.textspeech.TextSpeechRepositoryImpl
import mx.dev.cmg.android.code.ui.feature.textspeech.viewmodel.TextSpeechViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val textSpeechModule = module {
    single<TextSpeechDataSource> { TextSpeechDataSourceImpl(androidContext()) }
    singleOf(::TextSpeechRepositoryImpl) { bind<TextSpeechRepository>() }
    viewModelOf(::TextSpeechViewModel)
}