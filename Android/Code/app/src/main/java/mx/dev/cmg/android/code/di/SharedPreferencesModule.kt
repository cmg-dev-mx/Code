package mx.dev.cmg.android.code.di

import mx.dev.cmg.android.code.data.datasource.local.shared.CustomSharedPreferences
import mx.dev.cmg.android.code.data.datasource.local.shared.SharedPreferencesLocalSource
import mx.dev.cmg.android.code.data.datasource.local.shared.SharedPreferencesLocalSourceImpl
import mx.dev.cmg.android.code.data.repository.shared.SharedPreferencesRepository
import mx.dev.cmg.android.code.data.repository.shared.SharedPreferencesRepositoryImpl
import mx.dev.cmg.android.code.ui.feature.shared.viewmodel.SharedPreferencesViewModel
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val sharedPreferencesModule = module {
    singleOf(::CustomSharedPreferences)
    singleOf(::SharedPreferencesLocalSourceImpl) { bind<SharedPreferencesLocalSource>() }
    singleOf(::SharedPreferencesRepositoryImpl) { bind<SharedPreferencesRepository>() }
    viewModelOf(::SharedPreferencesViewModel)
}