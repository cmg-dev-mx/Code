package mx.dev.cmg.android.code.di

import mx.dev.cmg.android.code.data.datasource.ai.AiDataSource
import mx.dev.cmg.android.code.data.datasource.ai.AiDataSourceImpl
import mx.dev.cmg.android.code.data.repository.ai.AiRepository
import mx.dev.cmg.android.code.data.repository.ai.AiRepositoryImpl
import mx.dev.cmg.android.code.ui.feature.aiconversation.viewmodel.AiConversationViewModel
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val aiModule = module {
    singleOf(::AiDataSourceImpl) { bind<AiDataSource>() }
    singleOf(::AiRepositoryImpl) { bind<AiRepository>() }
    viewModelOf(::AiConversationViewModel)
}