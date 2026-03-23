package mx.dev.cmg.android.code.di

import mx.dev.cmg.android.code.ui.feature.aiconversation.viewmodel.AiConversationViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val aiModule = module {
    viewModelOf(::AiConversationViewModel)
}