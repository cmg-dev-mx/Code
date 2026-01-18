package mx.dev.cmg.android.code.di

import mx.dev.cmg.android.code.ui.feature.main.viewmodel.MainViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    viewModelOf(::MainViewModel)
}