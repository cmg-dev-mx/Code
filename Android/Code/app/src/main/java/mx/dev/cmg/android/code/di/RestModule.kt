package mx.dev.cmg.android.code.di

import mx.dev.cmg.android.code.ui.feature.rest.viewmodel.RestViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val restModule = module {
    viewModelOf(::RestViewModel)
}