package mx.dev.cmg.android.code.di

import mx.dev.cmg.android.code.ui.feature.web.viewmodel.WebMenuViewModel
import mx.dev.cmg.android.code.ui.feature.web.viewmodel.WebViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val webModule = module {
    viewModelOf(::WebViewModel)
    viewModelOf(::WebMenuViewModel)
}