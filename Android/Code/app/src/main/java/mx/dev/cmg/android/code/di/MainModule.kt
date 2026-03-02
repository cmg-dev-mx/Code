package mx.dev.cmg.android.code.di

import mx.dev.cmg.android.code.ui.feature.crash.viewmodel.CrashViewModel
import mx.dev.cmg.android.code.ui.feature.main.viewmodel.MainViewModel
import mx.dev.cmg.android.code.ui.feature.mvidemo.viewmodel.NameListViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val mainModule = module {
    viewModelOf(::MainViewModel)
    viewModelOf(::NameListViewModel)
    viewModelOf(::CrashViewModel)
}
