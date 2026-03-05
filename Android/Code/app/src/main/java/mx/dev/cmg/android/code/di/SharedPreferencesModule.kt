package mx.dev.cmg.android.code.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import mx.dev.cmg.android.code.data.datasource.local.shared.AppPreferences
import mx.dev.cmg.android.code.data.datasource.local.shared.SharedPreferencesLocalSource
import mx.dev.cmg.android.code.data.datasource.local.shared.SharedPreferencesLocalSourceImpl
import mx.dev.cmg.android.code.data.repository.shared.SharedPreferencesRepository
import mx.dev.cmg.android.code.data.repository.shared.SharedPreferencesRepositoryImpl
import mx.dev.cmg.android.code.ui.feature.sharedpreferences.viewmodel.SharedPreferencesViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = AppPreferences.DATA_STORE_NAME)

val sharedPreferencesModule = module {
    single<DataStore<Preferences>> { androidContext().dataStore }
    singleOf(::AppPreferences)
    singleOf(::SharedPreferencesLocalSourceImpl) { bind<SharedPreferencesLocalSource>() }
    singleOf(::SharedPreferencesRepositoryImpl) { bind<SharedPreferencesRepository>() }
    viewModelOf(::SharedPreferencesViewModel)
}